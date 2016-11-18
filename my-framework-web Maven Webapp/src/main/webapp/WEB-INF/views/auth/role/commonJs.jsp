<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>

<script type="text/javascript">

    //授予资源
    function grantResource(){
        var grid = $("#roleGrid");

        var roleId = grid.grid("option","selarrrow");

        if(roleId.length != 1){
            warning("请选择一条记录");
            return;
        }

        var url = "${ctx}/auth/role/openGrantResourceDialog?roleId="+roleId

        var rowData = grid.grid("getRowData",roleId[0]);

        dialog(
                "#grantResourceDialog",
                {
                    width : 400,
                    height : 700,
                    title : "授予资源",
                    url : url
                },
                [
                    {
                        text	:	"保存",
                        id		:	"grantResourceSaveButton",
                        click	:	function(){
                            var _this = $(this);
                            var tree = $("#grantResourceTree");
                            var selNode = tree.tree("getCheckedNodes",true);//获取全部勾选中的节点

                            //获得所有选中的资源ID
                            var resourceIds = [];
                            for(var i = 0 ; i<selNode.length ; i++){
                                resourceIds.push(selNode[i].id);
                            }
                            var addResourceIds = arrayCompared(resourceIds,grantResourceDialog.oldResourceIds);
                            var addResourceNames = "";
                            if(addResourceIds!=''){
                                var addResourceArray= new Array();
                                addResourceArray = addResourceIds.split(",");
                                for(var i=0;i<addResourceArray.length;i++){
                                    var addResourceId=addResourceArray[i];
                                    var node = tree.tree("getNodesByParam",'id',addResourceId,null)[0];
                                    addResourceNames += ","+node.name;
                                }
                                addResourceNames = addResourceIds==''? addResourceNames : addResourceNames.substring(1,addResourceNames.length);
                            }

                            var deleteResourceIds = arrayCompared(grantResourceDialog.oldResourceIds,resourceIds);
                            var deleteResourceNames = "";
                            if(deleteResourceIds!=''){
                                var deleteResourceArray = deleteResourceIds.split(",");
                                for(var i=0;i<deleteResourceArray.length;i++){
                                    var deleteResourceId=deleteResourceArray[i];
                                    var node = tree.tree("getNodesByParam",'id',deleteResourceId,null)[0];
                                    deleteResourceNames += ","+node.name;
                                }
                                deleteResourceNames = deleteResourceIds==''?deleteResourceNames : deleteResourceNames.substring(1,deleteResourceNames.length);
                            }

                            //未做任何修改
                            if(addResourceIds==''&&deleteResourceIds==''){
                                hide();
                                _this.dialog("close");
                                message("保存成功！");
                                return;
                            }


                            loading("正在保存...");
                            $.ajax({
                                type		:	'POST',
                                url			:	'${ctx}/auth/role/grantResource',
                                data		:	{
                                                    roleId : roleId.toString(),
                                                    addResourceIds : addResourceIds ,
                                                    deleteResourceIds : deleteResourceIds,
                                                    roleName : rowData.name ,
                                                    addResourceNames : addResourceNames ,
                                                    deleteResourceNames : deleteResourceNames
                                                },
                                dataType	:	'json',
                                success		:	function(data){
                                    message("保存成功");
                                    _this.dialog("close");
                                },
                                error		:	function(e){
                                    error(e);
                                },
                                complete	:	function(){
                                    hide();
                                }
                            })
                        }
                    },
                    {
                        text	:	"关闭",
                        id		:	"grantResourceCloseButton",
                        click	:	function(){
                            $(this).dialog("close");
                        }
                    }
                ]
        ).dialog("open");
    }

</script>