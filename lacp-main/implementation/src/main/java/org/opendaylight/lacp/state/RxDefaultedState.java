/*
 * Copyright (c) 2015 Dell Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.lacp.state;

import java.util.Arrays;


import org.opendaylight.lacp.core.LacpBpduInfo;
import org.opendaylight.lacp.core.LacpConst;
import org.opendaylight.lacp.inventory.LacpPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.opendaylight.lacp.inventory.LacpPort.PortParams;

public class RxDefaultedState extends RxState {

	private static final Logger LOG = LoggerFactory.getLogger(RxDefaultedState.class);
	public RxDefaultedState(){
		stateFlag = LacpConst.RX_STATES.RX_DEFAULTED;
	}
	public void executeStateAction(RxContext obj, LacpPort portObjRef,LacpBpduInfo pdu){

		/*
		1. update_Default_Selected
		2. recordDefault
		3. Actor_Oper_Port_State.Expired = FALSE
		*/

		stateFlag = LacpConst.RX_STATES.RX_DEFAULTED;
		updateDefaultSelected(portObjRef);
		recordDefault(portObjRef);
		portObjRef.setStateMachineBitSet((short)(portObjRef.getStateMachineBitSet() | LacpConst.PORT_MATCHED));
		portObjRef.setActorOperPortState((byte)(portObjRef.getActorOperPortState()
									& ~LacpConst.PORT_STATE_EXPIRED));
		obj.setState(this);

	}

	void updateDefaultSelected(LacpPort portObjRef)
	{
		final PortParams admin = portObjRef.getPartnerAdmin();
		final PortParams oper = portObjRef.getPartnerOper();


		if (admin.getPortNumber() != oper.getPortNumber() ||
			admin.getPortPriority() != oper.getPortPriority() ||
			(!Arrays.equals(admin.getSystem(),oper.getSystem())) ||
			admin.getSystemPriority() != oper.getSystemPriority() ||
			admin.getKey() != oper.getKey() ||
			(admin.getPortState() & LacpConst.PORT_STATE_AGGREGATION)!=
			(oper.getPortState() & LacpConst.PORT_STATE_AGGREGATION)) {
			portObjRef.setStateMachineBitSet((short)(portObjRef.getStateMachineBitSet() & ~LacpConst.PORT_SELECTED));
			LOG.info("RxDefaultedState updateDefaultSelected, setting the port={} to UNSELECTED", portObjRef.slaveGetPortId());
		}
	}

	public LacpConst.RX_STATES getStateFlag(){
		return stateFlag;
	}
	public void setStateFlag(LacpConst.RX_STATES state){
		stateFlag = state;
	}
}

