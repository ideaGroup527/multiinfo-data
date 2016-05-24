package com.kxm.servlet.download;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExcelCreatTest {

	@Test
	public void testExcelCreat()  {
		String[][] array = {{"1","2"},{"1","2"},{"1","2"}};
		try {
			new ExcelAdd("kxm2", array);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
