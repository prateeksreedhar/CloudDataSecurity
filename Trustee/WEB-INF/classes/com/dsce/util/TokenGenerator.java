package com.dsce.util;

import java.util.UUID;

public class TokenGenerator {

	public static String newToken() {
		return UUID.randomUUID().toString();
	}
}
