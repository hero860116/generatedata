package com.lwl.biz.ao;

import com.alibaba.biz.command.result.Result;
import com.alibaba.biz.command.result.ResultSupport;

/**
 * BaseAO
 * 
 * @create Mar 27, 2008
 * @author dongbai mailto:dongbai@taobao.com
 * 
 */
public class BaseAO extends BaseSession{

	public Result createResult() {
		return new ResultSupport(Boolean.FALSE);
	}

	public Result createResult(boolean isSucced) {
		return new ResultSupport(isSucced);
	}
}
