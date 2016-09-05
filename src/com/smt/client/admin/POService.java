/**
 * @author sumit
 * POService.java : Its the synchronous interface from RPC's Service interface
 * 					It inherits RemoteService and the RemoteServiceRelativePath
 * 					is basically to identify the Relative servlet class.
 
 *
 ** Here we are taking the  Service interface
 * for the POService
 * this interface extends another interface  RemoteService
 * when we extends an interface to as a RemoteService then it works as the
 * Synchronous interface in the GWT
 * we define all the method which are required in this interface
 * These methods are implemented on the server side in the GWT
 * because the GWt is divided into three part
 *  1.client....>
 *              we can put only the GUI related code in the client 
 *              we can,t put database or any other operation
 *              on the client side
 *  2.Server.....>
 *  we put database related or business related logic on the 
 *  Server side
 *  we can't put the GUI related logic on the server side
 *  we implement all the method of the Synchronous interface on the server side
 *  3.Shared.....> in the shared we put the logic that are shared by both by the client and server
 *So directly we can't access the server side data on the client 
 *side 
 *we have to make Remote Procedure Call(RPC)
 *the client is the Synchronous and server side data is the synchronous
 *in this case the browser will hang
 *So to make the Browser Asynchronous we have to make RPC connection.
 *We take to interface Synchronous another one is asynchronous
 *Asynchronous interface is automatically generated by the
 *
 */

package com.smt.client.admin;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.smt.client.vendor.VendorReqDAO;

//servlet url mapping pattern is "po" here
@RemoteServiceRelativePath("po")
public interface POService extends RemoteService {
	//all the undefined method which we implemented in the server side which is
	//POServiceImp.java servlet
	void PORequest(ArrayList<String> vendorid, String productType, String productConfig, int quantity);
	VendorReqDAO vendorPODashboard(String vid);
	void vendorComment(String vendorComment, String reqId);
	VendorReqDAO adminViewVendorReply();
	void adminReplyToVendor(String reqId, String status);
}