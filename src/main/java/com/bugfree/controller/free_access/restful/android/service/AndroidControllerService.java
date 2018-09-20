package com.bugfree.controller.free_access.restful.android.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugfree.common.KrenaiCONSTANTS;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.android.SupplierLoginSession;
import com.bugfree.repository.supplier.android.SupplierLoginSessionRepository;
import com.bugfree.service.status.StatusService;

@Service
public class AndroidControllerService {

	@Autowired
	private  StatusService statusService;
	@Autowired
	private  SupplierLoginSessionRepository supplierLoginSessionRepository;
	
	
	public  Supplier verifySupplier(String sessionId){
		Status activeStatus = statusService.findByStatusValue(KrenaiCONSTANTS.activeStatus);
		SupplierLoginSession loginSession = supplierLoginSessionRepository.findBySessionIdAndStatus(sessionId, activeStatus);
		return loginSession.getSupplier();
	}
	

	public void removeEarlierActiveSession(Supplier supplier, Status status) {
		System.out.println("****************status id *****************"+status.getStatusId());
		System.out.println("****************supplier id *****************"+supplier.getSupplierId());
		List<SupplierLoginSession> loginSession = supplierLoginSessionRepository.findBySupplierAndStatus(supplier, status);
		if(loginSession.size()>0){
			List<SupplierLoginSession> tempList = new ArrayList<SupplierLoginSession>();
			Status loggedOutSession = statusService.findByStatusValue(KrenaiCONSTANTS.loggedOutStatus);
			for(SupplierLoginSession sls :loginSession){
				sls.setStatus(loggedOutSession);
				tempList.add(sls);
			}
			supplierLoginSessionRepository.save(tempList);
		}
		
	}

}
