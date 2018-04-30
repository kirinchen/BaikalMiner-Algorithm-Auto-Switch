package com.surfm.baikalminer.config;

import org.apache.commons.lang3.StringUtils;

public enum Algo {
	cryptonight, cryptonight_lite("cryptonight-lite");

	private String selectName;

	private Algo() {
	}

	private Algo(String s) {
		selectName = s;
	}

	public String getSelectName() {
		return StringUtils.isNotEmpty(selectName) ? selectName : toString();
	}

}
