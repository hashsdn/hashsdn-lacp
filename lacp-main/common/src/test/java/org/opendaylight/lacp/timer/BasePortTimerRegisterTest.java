/*
 * Copyright (c) 2015 Dell Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opendaylight.lacp.timer.*;
public class BasePortTimerRegisterTest {
	BasePortTimerRegister baseTimerReg, b1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		baseTimerReg = new BasePortTimerRegister((short)10, 20L);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBasePortTimerRegister() throws Exception {
		BasePortTimerRegister b1 = new BasePortTimerRegister((short)30,40L);
		b1.setPortID((short)10);
		b1.setSystemID(10L);
	}

	@Test
	public void testSetPortID() throws Exception {
		baseTimerReg.setPortID((short)100);
	}

	@Test
	public void testSetSystemID() throws Exception {
		baseTimerReg.setSystemID(200L);
	}

	@Test
	public void testGetPortID() throws Exception {
		baseTimerReg.getPortID();
	}

	@Test
	public void testGetSystemID() throws Exception {
		baseTimerReg.getSystemID();
	}
}
