package com.cesgroup.catpcha;

import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

public class MyManageableImageCaptchaService extends DefaultManageableImageCaptchaService
{
	public MyManageableImageCaptchaService(CaptchaStore captchaStore, CaptchaEngine captchaEngine, int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize,
			int captchaStoreLoadBeforeGarbageCollection)
	{
		super(captchaStore, captchaEngine, minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize, captchaStoreLoadBeforeGarbageCollection);
	}

	public boolean hasCapcha(String id, String userCaptchaResponse)
	{
		return store.getCaptcha(id).validateResponse(userCaptchaResponse);
	}
}
