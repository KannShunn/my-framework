package com.cesgroup.core.vo;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 组件库grid 组件对应的分页对象
 * 
 * @author 国栋
 *
 */
public class PageVo
{
	public List<?> getData()
	{
		return data;
	}

	public Integer getPageNumber()
	{
		return pageNumber;
	}

	public Integer getPageSize()
	{
		return pageSize;
	}

	public Long getTotal()
	{
		return total;
	}

	public Integer getTotalPages()
	{
		return totalPages;
	}

	public void setData(List<?> data)
	{
		this.data = data;
	}

	public void setPageNumber(Integer pageNumber)
	{
		this.pageNumber = pageNumber;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	public void setTotal(Long total)
	{
		this.total = total;
	}

	public void setTotalPages(Integer totalPages)
	{
		this.totalPages = totalPages;
	}

	private List<?> data;

	private Integer pageNumber;
	private Integer pageSize;
	private Long total;
	private Integer totalPages;

	public static PageVo convertFromSpringJPAPage(Page<?> page)
	{
		PageVo result = new PageVo();
		if (page != null)
		{
			result.setData(page.getContent());
			result.setPageSize(page.getSize());
			result.setPageNumber(page.getNumber() + 1);
			result.setTotal(page.getTotalElements());
			result.setTotalPages(page.getTotalPages());
		}
		return result;
	}
}
