/**
 * @author sumit
 * Here we are receiving all the information from 
 * the stock table.
 * And here we have made a table where we are showing all
 * the available stocks for the particular request if the stock 
 * is available admin will grant it by clicking on grant button.
 */

package com.smt.client.stock;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smt.client.requestHistory.AdminAprovalCallback;
import com.smt.client.requestHistory.ReqService;
import com.smt.client.requestHistory.ReqServiceAsync;

public class CheckStockCallback implements AsyncCallback<StockDao>{
	/*
	 * take the VerticalPanel class and create the object of that class
	 * the vertical panel is used to add the GWT widgets vertically
	 * 
	 * */
	 VerticalPanel v1 = new VerticalPanel();
	 VerticalPanel v2 = new VerticalPanel();
	 VerticalPanel v3 = new VerticalPanel();
	 VerticalPanel v4 = new VerticalPanel();
	 VerticalPanel v5 = new VerticalPanel();
	 /*
		 * take the HoriZontal class and create the object of that class
		 * the horizontal panel is used to add the GWT widgets in the line Horizontally
		 * 
		 * 
		 * */
	 HorizontalPanel h = new HorizontalPanel();
	 /*here we are taking the Label
		 * The Label can contains only arbitrary text 
		 * and it can not be interpreted as HTML. 
		 * This widget uses a <div> element, 
		 * causing it to be displayed with block layout.
		 * */
	 Label l;
	  /* here we are defining the button widget
			 *  to add to perform click operation
			 *  The Button class, for example, publishes click events.
			 *   The associated handler interface is ClickHandler.
			 **/
	 Button btn;
	 ReqServiceAsync admin = GWT.create(ReqService.class);
	 StockServiceAsync onGrantDelete = GWT.create(StockService.class);
	@Override
	public void onFailure(Throwable caught) {
		System.out.println(caught.getLocalizedMessage());
	}

	@Override
	public void onSuccess(StockDao stock) {
		
		RootPanel.get("stock").clear();
		
		//arraylists for receiving all the items
		ArrayList<String> productId ;
		 ArrayList<String> productName ;
		 ArrayList<String> productConfig ;
		 ArrayList<String> warranty ;
		 
		 productId = stock.getProductId();
		 productName = stock.getProductName();
		 productConfig = stock.getProductConfig();
		 warranty = stock.getWarranty();
		 final String reqID = stock.getReqId();
		// RootPanel.get("adminDashboard").clear();
	 	 h = new HorizontalPanel();
	 	 h.clear();
	 	 int count = 3;
	 	 //content of the stock dashboard
	 	for(int i = 0 ; i < productId.size() ; i++) {
	 	 	btn = new Button("grant");
			btn.setStyleName("grantBtn"); 
	 	 	 String pid = productId.get(i);
	 	 	 final String proId = pid;
			 String p = productName.get(i);
			 String pc = productConfig.get(i);
			 String w = warranty.get(i);
			 v1.add(l = new Label(pid));
			 if(count % 2 == 0)
				 /*
					 * here we are setting the style for the 
					 * panels and widgets
					 * set Style is the provide the shape and color to the component
					 * so it becomes the more interesting and
					 * screen friendly
					 * we define the styling in the .css file and access that 
					 * simply calling the method setStyleName()
					 * and pass that name in the method*/
				 l.setStyleName("dashBoardPname");
			 else
				 l.setStyleName("dashBoardPnameEven");
			 v2.add(l = new Label(p));
			 if(count % 2 == 0)
				 l.setStyleName("dashBoardPname");
			 else
				 l.setStyleName("dashBoardPnameEven");
			 v3.add(l = new Label(pc));
			 if(count % 2 == 0)
				 l.setStyleName("dashBoardProdConfig");
			 else
				 l.setStyleName("dashBoardProdConfigEven");
			 v4.add(l = new Label(w));
			 if(count % 2 == 0)
				 l.setStyleName("dashBoardProdConfig");
			 else
				 l.setStyleName("dashBoardProdConfigEven");
			 v5.add(btn);	
			 adminGrant(reqID, proId);
			 
			 count++;
	 }
	 	
	 	 h.add(v1);
		 h.add(v2);
		 h.add(v3);
		 h.add(v4);
		 h.add(v5);
		 h.setStyleName("TableBody");
		 RootPanel.get("stock").add(h);
	}
	//to grant a particular request by seeing the stock is available or not 
	private void adminGrant(final String reqId, final String productId) {
		btn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				//Window.alert("grant");
				admin.adminAproval(reqId, new AdminAprovalCallback());
				onGrantDelete.deleteStock(productId, new DeleteStockCallback());
				
				
			}
		});
	}
}
