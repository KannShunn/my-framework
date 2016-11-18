package com.cesgroup.auth.user.vo;

public class UserImageVo
{
	private String urlPath;

	private String imageContent;

	private boolean success;

	public String getUrlPath()
	{
		return urlPath;
	}

	public String getImageContent()
	{
		return imageContent;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setUrlPath(String urlPath)
	{
		this.urlPath = urlPath;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public void setImageContent(String contentType, String encode)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("data:").append(contentType).append(";base64,").append(encode);
		this.imageContent = sb.toString();
	}
}
