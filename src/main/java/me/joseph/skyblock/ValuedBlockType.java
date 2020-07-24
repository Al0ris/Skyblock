package me.joseph.skyblock;

import lombok.Getter;

public enum ValuedBlockType {

	DIAMOND_BLOCK(100);

	@Getter
	private final double value;

	ValuedBlockType(double value) {
		this.value = value;
	}

}
