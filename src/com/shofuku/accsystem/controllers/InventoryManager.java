package com.shofuku.accsystem.controllers;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import com.shofuku.accsystem.domain.inventory.FinishedGood;
import com.shofuku.accsystem.domain.inventory.Ingredient;
import com.shofuku.accsystem.domain.inventory.Item;
import com.shofuku.accsystem.domain.inventory.ItemPricing;
import com.shofuku.accsystem.domain.inventory.Memo;
import com.shofuku.accsystem.domain.inventory.OfficeSupplies;
import com.shofuku.accsystem.domain.inventory.PurchaseOrderDetails;
import com.shofuku.accsystem.domain.inventory.RawMaterial;
import com.shofuku.accsystem.domain.inventory.TradedItem;
import com.shofuku.accsystem.domain.inventory.Utensils;
import com.shofuku.accsystem.domain.inventory.Warehouse;
import com.shofuku.accsystem.utils.HibernateUtil;
import com.shofuku.accsystem.utils.PurchaseOrderDetailHelper;
import com.shofuku.accsystem.utils.SASConstants;

/*
 * add business side logic in this class
 */
@SuppressWarnings("rawtypes")
public class InventoryManager extends BaseController{

	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public boolean addInventoryObject(Object inventoryObject,Session session) {
		return inventoryDao.save(inventoryObject,session);
	}

	public boolean addPersistingInventoryObject(Object inventoryObject,Session session) {
		return inventoryDao.persistingInsert(inventoryObject,session);
	}
	

	public boolean deleteInventoryByParameter(Object object, Class clazz,Session session) {
		return inventoryDao.deleteByParameter(object, clazz,session);
	}
	
	
	public boolean deletePersistingInventoryItem(Object object, Session session) {
		return inventoryDao.persistingDelete(object,session);
	}
	
	
	public boolean mergeInventoryByParameter(Object object, Class clazz,Session session) {
		return inventoryDao.mergeByParameter(object, clazz,session);
	}

	public boolean updateInventory(Object persistentObject,Session session) {
		return inventoryDao.update(persistentObject,session);
	}
	
	public boolean addStockStatus(Object inventoryObject,Session session) {
		return inventoryDao.saveStockStatus(inventoryObject,session);
	}
	
	public boolean updateStockStatus(Object persistentObject,Session session) {
		return inventoryDao.updateStockStatus(persistentObject,session);
	}
	
	public boolean updatePersistingInventoryObject(Object persistentObject,Session session) {
		return inventoryDao.persistingUpdate(persistentObject,session);
	}

	public List listInventoryByParameter(Class clazz, String parameter,
			String value,Session session) {
		return inventoryDao.listByParameter(clazz, parameter, value,session);
	}

	public List listInventoryByParameterLike(Class clazz, String parameter,
			String value,Session session) {
		return inventoryDao.listByParameterLike(clazz, parameter, value,session);
	}
	
	public List listInventoryByParametersLike(Class clazz, Map<String,Object> parameterMap,List parameterFields, String orderByString,
			Session session) {
		return inventoryDao.listByParametersLike(clazz, parameterMap, parameterFields,orderByString, session);
	}
	public List listByParameters(Class clazz, Map<String,Object> parameterMap,List parameterFields, String orderByString,
			Session session) {
		return inventoryDao.listByParameters(clazz, parameterMap, parameterFields,orderByString, session);
	}
	public boolean addPurchaseOrderDetails(Object purchaseOrderDetail,Session session) {
		return inventoryDao.save(purchaseOrderDetail,session);
	}
	
	public List getInventoryElementsByDate(Date date,
			String className,String parameter,Session session) {
		return  inventoryDao.getBetweenDatesWithOrderBy(date, date,className, parameter,session);
	}

	
	public Set<Ingredient> persistsIngredients(Set<Ingredient> ingredients,Session session) {
		return inventoryDao.persistsIngredients(ingredients,session);
	}
	
	public boolean persistMemo(Memo memo,Session session) {
		return inventoryDao.persistMemo(memo,session);
	}
	
	public List listAlphabeticalAscByParameter(Class clazz, String parameter,Session session) {
		return inventoryDao.listAlphabeticalAscByParameter(clazz, parameter,session);
	}
	
	public List searchFPTSByOrderRequisitionNo(Class clazz, String parameter, String value,Session session){
		return inventoryDao.searchFPTSByOrderRequisitionNo(clazz, parameter, value, session);
	}
	public List listByParameter(Class clazz, String parameter, String value,Session session) {
		return inventoryDao.listByParameter(clazz, parameter, value,session);
	}
	
	/*
	 * <p><b>Description:</b> This adds quantity per record to an item based on the quantityIn value of the incoming object.
	 * Handles RawMaterial,FinishedGood and TradedItem
	 * 
	 * </p>
	 */
	
	/**
	 * 
	 * @param originalItem.quantityPerRecord, quantityIn, quantityOut
	 * @param session
	 * @return
	 */
	
	private Warehouse updateItemWarehouseRecord(Warehouse warehouse,String itemCode, double quantityIn, double quantityOut) {
		warehouse.setLocationCode(inventoryDao.getUser().getLocation());
		warehouse.setItemCode(itemCode);
		warehouse.setQuantityPerRecord( (quantityIn + warehouse.getQuantityPerRecord()) - quantityOut);
		return warehouse;
	}
	
	public Warehouse getWarehouseBasedOnUserLocation(String itemCode, Set<Warehouse> warehouses) {
		Iterator iterator = warehouses.iterator();
		Warehouse warehouse = new Warehouse();
		boolean inList = false;
		while(iterator.hasNext()) {
			Warehouse tempWH = (Warehouse) iterator.next();
			if(tempWH.getLocationCode().equalsIgnoreCase(inventoryDao.getUser().getLocation())) {
				inList=true;
				warehouse=tempWH;
				break;
			}
		}
		
		if(!inList) {
			warehouse.setLocationCode(inventoryDao.getUser().getLocation());
			warehouse.setItemCode(itemCode);
		}
		
		return warehouse;
	}

	public Set<Warehouse> populateNewWarehousesSet(Set<Warehouse> oldWarehousesSet,
			Warehouse updatedWarehouse) {
		
		Set<Warehouse> newWarehouses =	new HashSet<Warehouse>(0);
		if(oldWarehousesSet !=null) {
			newWarehouses.add(updatedWarehouse);
			Iterator iterator = oldWarehousesSet.iterator();
			while(iterator.hasNext()) {
				Warehouse warehouse = (Warehouse) iterator.next();
				if(warehouse.getLocationCode().equalsIgnoreCase(inventoryDao.getUser().getLocation())) {
				}else {
					newWarehouses.add(warehouse);
				}
			}
		}
		return newWarehouses;
	}
	
	public boolean updateInventoryItemRecordCountFromOrder(Object object,Session session) {
				
		if (object instanceof RawMaterial) {
			RawMaterial incomingItem = (RawMaterial) object;
			RawMaterial originalItem = (RawMaterial)inventoryDao.load(incomingItem.getItemCode(),RawMaterial.class);
			
			//ADDED FOR WAREHOUSE IMPLEMENTATION
			if(originalItem != null) {
				if(originalItem.getWarehouses() !=null) {
					originalItem.setWarehouse(getWarehouseBasedOnUserLocation(originalItem.getItemCode(),originalItem.getWarehouses()));
					originalItem.setWarehouses(populateNewWarehousesSet(
															originalItem.getWarehouses(),
															updateItemWarehouseRecord(	originalItem.getWarehouse(), 
																						originalItem.getItemCode(), 
																						incomingItem.getQuantityIn(), 
																						incomingItem.getQuantityOut())));
				}
				return inventoryDao.updateInventoryPerRecordCount(originalItem, session);
			}
			//END WAREHOUSE IMPLEMENTATION
			
		}else if (object instanceof FinishedGood) {
			FinishedGood incomingItem = (FinishedGood) object;
			FinishedGood originalItem = (FinishedGood)inventoryDao.load(incomingItem.getProductCode(),FinishedGood.class);
			
			//ADDED FOR WAREHOUSE IMPLEMENTATION
			if(originalItem != null) {
				if(originalItem.getWarehouses() !=null) {
					originalItem.setWarehouse(getWarehouseBasedOnUserLocation(originalItem.getItemCode(),originalItem.getWarehouses()));
					originalItem.setWarehouses(populateNewWarehousesSet(
															originalItem.getWarehouses(),
															updateItemWarehouseRecord(	originalItem.getWarehouse(), 
																						originalItem.getItemCode(), 
																						incomingItem.getQuantityIn(), 
																						incomingItem.getQuantityOut())));
				}
				return inventoryDao.updateInventoryPerRecordCount(originalItem, session);
			}
			//END WAREHOUSE IMPLEMENTATION	
			
		}else if (object instanceof TradedItem) {
			TradedItem incomingItem = (TradedItem) object;
			TradedItem originalItem = (TradedItem)inventoryDao.load(incomingItem.getItemCode(),TradedItem.class);
			
			//ADDED FOR WAREHOUSE IMPLEMENTATION
			if(originalItem != null) {
				if(originalItem.getWarehouses() !=null) {
					originalItem.setWarehouse(getWarehouseBasedOnUserLocation(originalItem.getItemCode(),originalItem.getWarehouses()));
					originalItem.setWarehouses(populateNewWarehousesSet(
															originalItem.getWarehouses(),
															updateItemWarehouseRecord(	originalItem.getWarehouse(), 
																						originalItem.getItemCode(), 
																						incomingItem.getQuantityIn(), 
																						incomingItem.getQuantityOut())));
				}
				return inventoryDao.updateInventoryPerRecordCount(originalItem, session);
			}
			//END WAREHOUSE IMPLEMENTATION
		}else if (object instanceof Utensils) {
			Utensils incomingItem = (Utensils) object;
			Utensils originalItem = (Utensils)inventoryDao.load(incomingItem.getItemCode(),Utensils.class);
			
			//ADDED FOR WAREHOUSE IMPLEMENTATION
			if(originalItem != null) {
				if(originalItem.getWarehouses() !=null) {
					originalItem.setWarehouse(getWarehouseBasedOnUserLocation(originalItem.getItemCode(),originalItem.getWarehouses()));
					originalItem.setWarehouses(populateNewWarehousesSet(
															originalItem.getWarehouses(),
															updateItemWarehouseRecord(	originalItem.getWarehouse(), 
																						originalItem.getItemCode(), 
																						incomingItem.getQuantityIn(), 
																						incomingItem.getQuantityOut())));
				}
				return inventoryDao.updateInventoryPerRecordCount(originalItem, session);
			}
			//END WAREHOUSE IMPLEMENTATION
		}else if (object instanceof OfficeSupplies) {
			OfficeSupplies incomingItem = (OfficeSupplies) object;
			OfficeSupplies originalItem = (OfficeSupplies)inventoryDao.load(incomingItem.getItemCode(),OfficeSupplies.class);
			
			//ADDED FOR WAREHOUSE IMPLEMENTATION
			if(originalItem != null) {
				if(originalItem.getWarehouses() !=null) {
					originalItem.setWarehouse(getWarehouseBasedOnUserLocation(originalItem.getItemCode(),originalItem.getWarehouses()));
					originalItem.setWarehouses(populateNewWarehousesSet(
															originalItem.getWarehouses(),
															updateItemWarehouseRecord(	originalItem.getWarehouse(), 
																						originalItem.getItemCode(), 
																						incomingItem.getQuantityIn(), 
																						incomingItem.getQuantityOut())));
				}
				return inventoryDao.updateInventoryPerRecordCount(originalItem, session);
			}
			//END WAREHOUSE IMPLEMENTATION
		}

		//passed an unknown item type
		return false;

	}
	
	
	

	/*
	 * <p><b>Description:</b> This adds quantity per record to an item based on the quantityIn value of the incoming object.
	 * Handles RawMaterial,FinishedGood and TradedItem
	 * 
	 * </p>
	 */
	@Deprecated //see updateInventoryItemRecordCountFromOrder
	public boolean addsInventoryItem(Object object,Session session) {
				
		if (object instanceof RawMaterial) {
			RawMaterial incomingItem = (RawMaterial) object;
			RawMaterial originalItem = (RawMaterial)inventoryDao.load(incomingItem.getItemCode(),RawMaterial.class);
			
			//ADDED FOR WAREHOUSE IMPLEMENTATION
			if(originalItem != null) {
				if(originalItem.getWarehouse()!=null) {
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), incomingItem.getQuantityIn(), incomingItem.getQuantityOut()));
				}else {
					Set<Warehouse> newWarehouses =	new HashSet<Warehouse>(0);
					if(originalItem.getWarehouses() !=null) {
						Iterator iterator = originalItem.getWarehouses().iterator();
						while(iterator.hasNext()) {
							Warehouse warehouse = (Warehouse) iterator.next();
							if(warehouse.getLocationCode().equalsIgnoreCase(inventoryDao.getUser().getLocation()))
								originalItem.setWarehouse(warehouse);
							else
								newWarehouses.add(warehouse);
						}
					}
					newWarehouses.add(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), incomingItem.getQuantityIn(), incomingItem.getQuantityOut()));
					originalItem.setWarehouses(newWarehouses);
				}
				return inventoryDao.updateInventoryPerRecordCount(originalItem, session);
			}
			//END WAREHOUSE IMPLEMENTATION
			
			
		}else if (object instanceof FinishedGood) {
			FinishedGood incomingItem = (FinishedGood) object;
			FinishedGood originalItem = (FinishedGood)inventoryDao.load(incomingItem.getProductCode(),FinishedGood.class);
			
			if(originalItem != null) {
				if(originalItem.getWarehouse()!=null) {
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getProductCode(), incomingItem.getQuantityIn(), 0));
				}else {
					originalItem.setWarehouse(new Warehouse());
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getProductCode(), incomingItem.getQuantityIn(), 0));
				}
				return inventoryDao.updateInventoryPerRecordCount(originalItem, session);
			}		
			
		}else if (object instanceof TradedItem) {
			TradedItem incomingItem = (TradedItem) object;
			TradedItem originalItem = (TradedItem)inventoryDao.load(incomingItem.getItemCode(),TradedItem.class);
			
			if(originalItem != null) {
				if(originalItem.getWarehouse()!=null) {
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), incomingItem.getQuantityIn(), 0));
				}else {
					originalItem.setWarehouse(new Warehouse());
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), incomingItem.getQuantityIn(), 0));
				}
				return inventoryDao.updateInventoryPerRecordCount(originalItem, session);
			}	
		}else if (object instanceof Utensils) {
			Utensils incomingItem = (Utensils) object;
			Utensils originalItem = (Utensils)inventoryDao.load(incomingItem.getItemCode(),Utensils.class);
			
			if(originalItem != null) {
				if(originalItem.getWarehouse()!=null) {
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), incomingItem.getQuantityIn(), 0));
				}else {
					originalItem.setWarehouse(new Warehouse());
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), incomingItem.getQuantityIn(), 0));
				}
				return inventoryDao.updateInventoryPerRecordCount(originalItem, session);
			}	
		}else if (object instanceof OfficeSupplies) {
			OfficeSupplies incomingItem = (OfficeSupplies) object;
			OfficeSupplies originalItem = (OfficeSupplies)inventoryDao.load(incomingItem.getItemCode(),OfficeSupplies.class);
			
			if(originalItem != null) {
				if(originalItem.getWarehouse()!=null) {
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), incomingItem.getQuantityIn(), 0));
				}else {
					originalItem.setWarehouse(new Warehouse());
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), incomingItem.getQuantityIn(), 0));
				}
				return inventoryDao.updateInventoryPerRecordCount(originalItem, session);
			}	
		}

		//passed an unknown item type
		return false;

	}
	
	/*
	 * <p><b>Description:</b> This deducts quantityOut of the incoming object to the existing quantityPerRecord.
	 * Handles RawMaterial,FinishedGood and TradedItem
	 * 
	 * </p>
	 */
	@Deprecated //see updateInventoryItemRecordCountFromOrder
	public boolean deductsInventoryItem(Object object,Session session) {
				
		if (object instanceof RawMaterial) {
			RawMaterial incomingItem = (RawMaterial) object;
			RawMaterial originalItem = (RawMaterial)inventoryDao.load(incomingItem.getItemCode(),RawMaterial.class);
			
			//ADDED FOR WAREHOUSE IMPLEMENTATION
			if(originalItem != null) {
				if(originalItem.getWarehouse()!=null) {
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), incomingItem.getQuantityIn(), incomingItem.getQuantityOut()));
				}else {
					Set<Warehouse> newWarehouses =	new HashSet<Warehouse>(0);
					if(originalItem.getWarehouses() !=null) {
						Iterator iterator = originalItem.getWarehouses().iterator();
						while(iterator.hasNext()) {
							Warehouse warehouse = (Warehouse) iterator.next();
							if(warehouse.getLocationCode().equalsIgnoreCase(inventoryDao.getUser().getLocation()))
								originalItem.setWarehouse(warehouse);
							else
								newWarehouses.add(warehouse);
						}
					}
					newWarehouses.add(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), incomingItem.getQuantityIn(), incomingItem.getQuantityOut()));
					originalItem.setWarehouses(newWarehouses);
				}
				return inventoryDao.updateInventoryPerRecordCount(originalItem, session);
			}
			//END WAREHOUSE IMPLEMENTATION
			
		}else if (object instanceof FinishedGood) {
			FinishedGood incomingItem = (FinishedGood) object;
			FinishedGood originalItem = (FinishedGood)inventoryDao.load(incomingItem.getProductCode(),FinishedGood.class);
			
			if(originalItem != null) {
				if(originalItem.getWarehouse()!=null) {
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getProductCode(), 0, incomingItem.getQuantityOut()));
				}else {
					originalItem.setWarehouse(new Warehouse());
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getProductCode(), 0, incomingItem.getQuantityOut()));
				}
				return inventoryDao.updateInventoryPerRecordCount(originalItem, session);
			}	
			
		}else if (object instanceof TradedItem) {
			TradedItem incomingItem = (TradedItem) object;
			TradedItem originalItem = (TradedItem)inventoryDao.load(incomingItem.getItemCode(),TradedItem.class);
			
			if(originalItem != null) {
				if(originalItem.getWarehouse()!=null) {
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), 0, incomingItem.getQuantityOut()));
				}else {
					originalItem.setWarehouse(new Warehouse());
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), 0, incomingItem.getQuantityOut()));
				}
				return inventoryDao.updateInventoryPerRecordCount(originalItem, session);
			}	
		}else if (object instanceof Utensils) {
			Utensils incomingItem = (Utensils) object;
			Utensils originalItem = (Utensils)inventoryDao.load(incomingItem.getItemCode(),Utensils.class);
			
			if(originalItem != null) {
				if(originalItem.getWarehouse()!=null) {
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), 0, incomingItem.getQuantityOut()));
				}else {
					originalItem.setWarehouse(new Warehouse());
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), 0, incomingItem.getQuantityOut()));
				}
				return inventoryDao.updateInventoryPerRecordCount(originalItem, session);
			}	
		}else if (object instanceof OfficeSupplies) {
			OfficeSupplies incomingItem = (OfficeSupplies) object;
			OfficeSupplies originalItem = (OfficeSupplies)inventoryDao.load(incomingItem.getItemCode(),OfficeSupplies.class);
			
			if(originalItem != null) {
				if(originalItem.getWarehouse()!=null) {
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), 0, incomingItem.getQuantityOut()));
				}else {
					originalItem.setWarehouse(new Warehouse());
					originalItem.setWarehouse(updateItemWarehouseRecord(originalItem.getWarehouse(), originalItem.getItemCode(), 0, incomingItem.getQuantityOut()));
				}
				return inventoryDao.updateInventoryPerRecordCount(originalItem, session);
			}	
		}
		//passed an unknown item type
		return false;
	}
	
	//method called from add
	public void updateInventoryFromOrders(
			PurchaseOrderDetailHelper poDetailsHelper) throws Exception {
			Iterator itr = poDetailsHelper.getPurchaseOrderDetailsSet().iterator();
			
			Session session = getSession();
			
			Object object= null;
			
			List<Item> fullItemList = getAllItemList(session);
			
			while(itr.hasNext()) {
				PurchaseOrderDetails podetails = (PurchaseOrderDetails) itr.next();
				if(poDetailsHelper.getOrderDate()!=null) {
					podetails.setOrderCreatedDate(poDetailsHelper.getOrderDate());
				}
				
				Item item = getInventoryObject(podetails.getItemCode(),fullItemList);
				
				try {
				if(item.getItemType().equalsIgnoreCase("rawMat")) {
					object = (RawMaterial)inventoryDao.load(podetails.getItemCode(), RawMaterial.class);	
					((RawMaterial)object).setQuantityIn(podetails.getQuantityIn());
					((RawMaterial)object).setQuantityOut(podetails.getQuantityOut());
				}else if(item.getItemType().equalsIgnoreCase("tradedItems")) {
					object = (TradedItem)inventoryDao.load(podetails.getItemCode(),TradedItem.class);
					((TradedItem)object).setQuantityIn(podetails.getQuantityIn());
					((TradedItem)object).setQuantityOut(podetails.getQuantityOut());
				}else if(item.getItemType().equalsIgnoreCase("utensils")) {
					object = (Utensils)inventoryDao.load(podetails.getItemCode(),Utensils.class);
					((Utensils)object).setQuantityIn(podetails.getQuantityIn());
					((Utensils)object).setQuantityOut(podetails.getQuantityOut());
				}else if(item.getItemType().equalsIgnoreCase("ofcSup")) {
					object = (OfficeSupplies)inventoryDao.load(podetails.getItemCode(),OfficeSupplies.class);
					((OfficeSupplies)object).setQuantityIn(podetails.getQuantityIn());
					((OfficeSupplies)object).setQuantityOut(podetails.getQuantityOut());
				}else if(item.getItemType().equalsIgnoreCase("finGood")) {
					object = (FinishedGood)inventoryDao.load(podetails.getItemCode(),FinishedGood.class);
					((FinishedGood)object).setQuantityIn(podetails.getQuantityIn());
					((FinishedGood)object).setQuantityOut(podetails.getQuantityOut());
				}
				
				if(object!=null) {
					updateInventoryItemRecordCountFromOrder(object, session);
				}
				}catch(NullPointerException npe) {
					//catch unlisted Items null pointer on inventory update
					//do nothing
				}
			}
		inventoryDao.commitChanges(session);
	}
	
	public void commitChanges(Session session) {
		inventoryDao.commitChanges(session);		
	}

	private Item getInventoryObject(String itemCode, List<Item> fullItemList) {
		Item itemFound = new Item();
		for(Item item: fullItemList){
				if(item.getItemCode().equalsIgnoreCase(itemCode)){
					itemFound  = item; 
			}
		}	
		return itemFound;
	}

	public Object setQinAndQoutBasedOnItemType(PurchaseOrderDetails poDetails,String inventoryMovement) {
		Object object= null;
		object = (RawMaterial)inventoryDao.load(poDetails.getItemCode(), RawMaterial.class);
		if(object!=null) {
			if(inventoryMovement.equalsIgnoreCase(SASConstants.ADD)) {
			((RawMaterial)object).setQuantityIn(poDetails.getQuantity());
			}else {
			((RawMaterial)object).setQuantityOut(poDetails.getQuantity());
			}
			return object;
		}else {
			object = (TradedItem)inventoryDao.load(poDetails.getItemCode(),TradedItem.class);
			if(object!=null) {
				if(inventoryMovement.equalsIgnoreCase(SASConstants.ADD)) {
				((TradedItem)object).setQuantityIn(poDetails.getQuantity());
				}else {
				((TradedItem)object).setQuantityOut(poDetails.getQuantity());
				}
				return object;
			}else {
				object = (FinishedGood)inventoryDao.load(poDetails.getItemCode(),FinishedGood.class);
				if(object!=null) {
					if(inventoryMovement.equalsIgnoreCase(SASConstants.ADD)) {
					((FinishedGood)object).setQuantityIn(poDetails.getQuantity());
					}else {
					((FinishedGood)object).setQuantityOut(poDetails.getQuantity());
					}
					return object;
				}else {
					object = (Utensils)inventoryDao.load(poDetails.getItemCode(),Utensils.class);
					if(object!=null) {
						if(inventoryMovement.equalsIgnoreCase(SASConstants.ADD)) {
						((Utensils)object).setQuantityIn(poDetails.getQuantity());
						}else{
						((Utensils)object).setQuantityOut(poDetails.getQuantity());
						}
						return object;
					}else{
						object = (OfficeSupplies)inventoryDao.load(poDetails.getItemCode(),OfficeSupplies.class);
						if(object!=null) {
							if(inventoryMovement.equalsIgnoreCase(SASConstants.ADD)) {
							((OfficeSupplies)object).setQuantityIn(poDetails.getQuantity());
							}else{
							((OfficeSupplies)object).setQuantityOut(poDetails.getQuantity());
							}
							return object;
						}else{
							return null;
						}
					}
				}
			}
		}
	}
/*
	//method called from update
	public void updateInventoryFromOrders(
			Set<PurchaseOrderDetails> purchaseOrderDetails,
			PurchaseOrderDetailHelper poDetailsHelper, String string,Session session) {
		try {
			Map<String,Double> mapChangesInQty = new HashMap<String,Double>();
			List<PurchaseOrderDetails> listToUpdate = new ArrayList<PurchaseOrderDetails>();
			
			//assign order record to a map
			Iterator drItr = purchaseOrderDetails.iterator();
			while(drItr.hasNext()) {
				PurchaseOrderDetails drOrder = (PurchaseOrderDetails) drItr.next();
				mapChangesInQty.put(drOrder.getItemCode(), drOrder.getQuantity());
			}
			
			//identifies which entries have changes by comparing order record map to the new orders
			drItr = poDetailsHelper.getPurchaseOrderDetailsSet().iterator();
			while(drItr.hasNext()) {
				PurchaseOrderDetails drOrder = (PurchaseOrderDetails) drItr.next();
				if(mapChangesInQty.containsKey(drOrder.getItemCode())) {
					double oldValue = mapChangesInQty.get(drOrder.getItemCode());
					double newValue = drOrder.getQuantity();
					mapChangesInQty.put(drOrder.getItemCode(), oldValue-newValue);
					listToUpdate.add(drOrder);
					//mapChangesInQty now has the difference in qty of the old and new orders
				}
			}
			
			
			//apply the change in qty to the appropriate item may it be fin/raw
			Iterator itr = listToUpdate.iterator();
				while(itr.hasNext()) {
					PurchaseOrderDetails podetails = (PurchaseOrderDetails) itr.next();
					double multiplier=-1;
					if(string.equalsIgnoreCase("rr")) {
						RawMaterial rawMaterial = (RawMaterial)dao.load(podetails.getItemCode(), RawMaterial.class);
						if(rawMaterial!=null) {
							rawMaterial.setQuantityPerRecord(rawMaterial.getQuantityPerRecord() + (mapChangesInQty.get(rawMaterial.getItemCode())*multiplier));
							dao.update(rawMaterial,session);
						}
					}
					 if(string.equalsIgnoreCase("dr")) {
						 FinishedGood finGood = (FinishedGood)dao.load(podetails.getItemCode(), FinishedGood.class);
						 if(finGood!=null) {
								finGood.setQuantityPerRecord(finGood.getQuantityPerRecord()+mapChangesInQty.get(finGood.getProductCode()));
								dao.update(finGood,session);
							}
					 }
				}
		}catch(Exception e) {
				e.printStackTrace();
	}
	}
	//method called from delete
	public void updateInventoryFromOrders(
			Set<PurchaseOrderDetails> purchaseOrderDetails, String string,Session session) {
		try {
			Iterator itr = purchaseOrderDetails.iterator();
			while(itr.hasNext()) {
				PurchaseOrderDetails podetails = (PurchaseOrderDetails) itr.next();

				if(string.equalsIgnoreCase("rr")) {
					RawMaterial rawMaterial = (RawMaterial)dao.load(podetails.getItemCode(), RawMaterial.class);
					if(rawMaterial!=null) {
						rawMaterial.setQuantityPerRecord(rawMaterial.getQuantityPerRecord()-podetails.getQuantity());
						dao.update(rawMaterial,session);
					}
				}
				if(string.equalsIgnoreCase("dr")) {
					FinishedGood finGood = (FinishedGood)dao.load(podetails.getItemCode(), FinishedGood.class);
					if(finGood!=null) {
						finGood.setQuantityPerRecord(finGood.getQuantityPerRecord()+podetails.getQuantity());
						dao.update(finGood,session);
					}
				}
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
	}
	*/
	
	
	//loadIngredients
	public Ingredient loadIngredientPrices(Ingredient originalIngredient,Session session){
		Ingredient ingredient = null;
		try{
		List list = inventoryDao.listByParameter(RawMaterial.class, "itemCode",
				originalIngredient.getProductCode(), session);
		RawMaterial item = (RawMaterial) list.get(0);
		ingredient = new Ingredient(
				item.getItemCode(),
				item.getDescription(),
				originalIngredient.getQuantity(),
				item.getUnitOfMeasurement(),
				item.getItemPricing().getCompanyOwnedStandardPricePerUnit()
						* originalIngredient.getQuantity(),
				item.getItemPricing().getCompanyOwnedActualPricePerUnit() * originalIngredient.getQuantity(),
				item.getItemPricing().getCompanyOwnedTransferPricePerUnit()
						* originalIngredient.getQuantity());
		}catch (IndexOutOfBoundsException iobe) {
			try{
				List list = inventoryDao.listByParameter(FinishedGood.class, "productCode",
					originalIngredient.getProductCode(), session);
			FinishedGood item = (FinishedGood) list.get(0);
			ingredient = new Ingredient(
					item.getProductCode(),
					item.getDescription(),
					originalIngredient.getQuantity(),
					item.getUnitOfMeasurement(),
					item.getItemPricing().getCompanyOwnedStandardPricePerUnit()
							* originalIngredient.getQuantity(),
					item.getItemPricing().getCompanyOwnedActualPricePerUnit() * originalIngredient.getQuantity(),
					item.getItemPricing().getCompanyOwnedTransferPricePerUnit()
							* originalIngredient.getQuantity());
			}catch(IndexOutOfBoundsException ioeb2) {
				try{
				List list = inventoryDao.listByParameter(TradedItem.class, "itemCode",
						originalIngredient.getProductCode(), session);
				TradedItem item = (TradedItem) list.get(0);
				ingredient = new Ingredient(
						item.getItemCode(),
						item.getDescription(),
						originalIngredient.getQuantity(),
						item.getUnitOfMeasurement(),
						item.getItemPricing().getCompanyOwnedStandardPricePerUnit()
								* originalIngredient.getQuantity(),
						item.getItemPricing().getCompanyOwnedActualPricePerUnit() * originalIngredient.getQuantity(),
						item.getItemPricing().getCompanyOwnedTransferPricePerUnit()
								* originalIngredient.getQuantity());
				}
				//added utensils
				catch(IndexOutOfBoundsException ioeb3){
					try{
					List list = inventoryDao.listByParameter(Utensils.class, "itemCode",
							originalIngredient.getProductCode(), session);
					Utensils item = (Utensils) list.get(0);
					ingredient = new Ingredient(
							item.getItemCode(),
							item.getDescription(),
							originalIngredient.getQuantity(),
							item.getUnitOfMeasurement(),
							item.getItemPricing().getCompanyOwnedStandardPricePerUnit()
									* originalIngredient.getQuantity(),
							item.getItemPricing().getCompanyOwnedActualPricePerUnit() * originalIngredient.getQuantity(),
							item.getItemPricing().getCompanyOwnedTransferPricePerUnit()
									* originalIngredient.getQuantity());
					}
					//added Office supplies
					catch(IndexOutOfBoundsException ioeb4){
						List list = inventoryDao.listByParameter(OfficeSupplies.class, "itemCode",
								originalIngredient.getProductCode(), session);
						OfficeSupplies item = (OfficeSupplies) list.get(0);
						ingredient = new Ingredient(
								item.getItemCode(),
								item.getDescription(),
								originalIngredient.getQuantity(),
								item.getUnitOfMeasurement(),
								item.getItemPricing().getCompanyOwnedStandardPricePerUnit()
										* originalIngredient.getQuantity(),
								item.getItemPricing().getCompanyOwnedActualPricePerUnit() * originalIngredient.getQuantity(),
								item.getItemPricing().getCompanyOwnedTransferPricePerUnit()
										* originalIngredient.getQuantity());
						}
					
					}
				}
			}
		return ingredient;
	}
	
	public List loadItemListFromRawAndFin(Session session){
		
		List rawAndFinList = inventoryDao.listAlphabeticalAscByParameter(RawMaterial.class, "itemCode",session);
		List finList = inventoryDao.listAlphabeticalAscByParameter(FinishedGood.class, "productCode", session);
		List tradedItemList = inventoryDao.listAlphabeticalAscByParameter(TradedItem.class, "itemCode", session);
		List utensilsList = inventoryDao.listAlphabeticalAscByParameter(Utensils.class, "itemCode", session);
		List ofcSupList = inventoryDao.listAlphabeticalAscByParameter(OfficeSupplies.class, "itemCode", session);
		
		Iterator finListItr = finList.iterator();
		Iterator tradedListItr = tradedItemList.iterator();
		Iterator utensilsListItr = utensilsList.iterator();
		Iterator ofcSupListItr = ofcSupList.iterator();
		
		while(finListItr.hasNext()){
			FinishedGood finGood = (FinishedGood) finListItr.next();
			
			rawAndFinList.add(new RawMaterial(finGood.getProductCode(), finGood.getDescription(), finGood.getUnitOfMeasurement(), finGood.getItemPricing()));
			//rawAndFinList.add(new RawMaterial(ti.getItemCode(), ti.getDescription(), ti.getUnitOfMeasurement(), ti.getItemPricing()));
		}
		while(tradedListItr.hasNext()){
			TradedItem ti = (TradedItem) tradedListItr.next();
			rawAndFinList.add(new RawMaterial(ti.getItemCode(), ti.getDescription(), ti.getUnitOfMeasurement(), ti.getItemPricing()));
		}
		//utensils
		while(utensilsListItr.hasNext()){
			Utensils u = (Utensils) utensilsListItr.next();
			rawAndFinList.add(new RawMaterial(u.getItemCode(), u.getDescription(), u.getUnitOfMeasurement(), u.getItemPricing()));
		}
		//office supplies
		while(ofcSupListItr.hasNext()){
			OfficeSupplies os = (OfficeSupplies) ofcSupListItr.next();
			rawAndFinList.add(new RawMaterial(os.getItemCode(), os.getDescription(), os.getUnitOfMeasurement(), os.getItemPricing()));
		} 
		return rawAndFinList;  
	}
	
	//used to generate new Supplier Order Form
	public Map getLatestInventoryItemUom(Session session){
		Map<String, String> itemsWithUom = new HashMap();
		
		List rawAndFinList = loadItemListFromRawAndFin(session);
		
		Iterator itemIterator = rawAndFinList.iterator();
		while(itemIterator.hasNext()){
			RawMaterial rawMaterial = (RawMaterial)itemIterator.next();
			itemsWithUom.put(rawMaterial.getItemCode(), rawMaterial.getUnitOfMeasurement());
		}
		
		return itemsWithUom;
	}
	
	public double getItemPricingByItemCodeAndParameter(Session session, String itemCode, String customerType, String priceType) {
		ItemPricing itemPricing = inventoryDao.getItemPricingByItemCodeAndParameter(session, itemCode);
		double price = 0.0;

		/*
		 * F = Franchise
		 * C = Commisary
		 * CC = Company Owned
		 */
		
		try {
		if(customerType.equalsIgnoreCase("F")) {
			switch(priceType.toLowerCase()) {
			case "actual": price = itemPricing.getFranchiseActualPricePerUnit();break;
			case "transfer": price = itemPricing.getFranchiseTransferPricePerUnit();break;
			case "standard": price = itemPricing.getFranchiseStandardPricePerUnit();break;
			}
		}else if(customerType.equalsIgnoreCase("C")) {
			switch(priceType.toLowerCase()) {
			case "actual": price = itemPricing.getCompanyOwnedActualPricePerUnit();break;
			case "transfer": price = itemPricing.getCompanyOwnedTransferPricePerUnit();break;
			case "standard": price = itemPricing.getCompanyOwnedStandardPricePerUnit();break;
			}
		}else if(customerType.equalsIgnoreCase("CC")) {
			switch(priceType.toLowerCase()) {
			case "actual": price = itemPricing.getCompanyOwnedActualPricePerUnit();break;
			case "transfer": price = itemPricing.getCompanyOwnedTransferPricePerUnit();break;
			case "standard": price = itemPricing.getCompanyOwnedStandardPricePerUnit();break;
			}
		}else {
			//By default we get the franchise price will have to confirm with business
			switch(priceType.toLowerCase()) {
			case "Actual": price = itemPricing.getCompanyOwnedActualPricePerUnit();
			case "Transfer": price = itemPricing.getCompanyOwnedTransferPricePerUnit();
			case "Standard": price = itemPricing.getCompanyOwnedStandardPricePerUnit();
			}
		}
		}catch(Exception e){
			return formatPrice(0.0);
		}
		
		return formatPrice(price);
	}
	
	public double formatPrice(double value) {
		DecimalFormat df = new DecimalFormat("########0.00##");
		return Double.valueOf(df.format(value));
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Item> getAllItemList(Session session) {
		Iterator iterator = null;
		
		List<RawMaterial> rawMatList =listAlphabeticalAscByParameter(RawMaterial.class, "subClassification",session);
		List<TradedItem> tradedItemList =listAlphabeticalAscByParameter(TradedItem.class, "subClassification",session);
		List<Utensils> utensilsList =listAlphabeticalAscByParameter(Utensils.class, "subClassification",session);
		List<OfficeSupplies> ofcSupList =listAlphabeticalAscByParameter(OfficeSupplies.class, "subClassification",session);
		List<FinishedGood> finList = listAlphabeticalAscByParameter(FinishedGood.class, "subClassification", session);
		
		ArrayList<Item> tempList = new ArrayList<Item>();
		
		iterator = rawMatList.iterator();
		while(iterator.hasNext()) {
			RawMaterial rawMaterial = (RawMaterial) iterator.next();
				//START: 2013 - PHASE 3 : PROJECT 4: MARK
				Item item = new Item(rawMaterial.getItemCode(), rawMaterial.getDescription(), rawMaterial.getUnitOfMeasurement(),
						rawMaterial.getClassification(), rawMaterial.getSubClassification(),rawMaterial.getIsVattable());
				//END: 2013 - PHASE 3 : PROJECT 4: MARK
				item.setItemType("rawMat");
				tempList.add(item);
				
		}
		
		iterator = tradedItemList.iterator();
		while(iterator.hasNext()) {
			TradedItem tradedItem = (TradedItem) iterator.next();
				//START: 2013 - PHASE 3 : PROJECT 4: AZ
				Item item = new Item(tradedItem.getItemCode(), tradedItem.getDescription(), tradedItem.getUnitOfMeasurement(),
						tradedItem.getClassification(), tradedItem.getSubClassification(),tradedItem.getIsVattable());
				//END: 2013 - PHASE 3 : PROJECT 4: AZ
				item.setItemType("tradedItems");
				tempList.add(item);
		}
		
		iterator = utensilsList.iterator();
		while(iterator.hasNext()) {
			Utensils u = (Utensils) iterator.next();
				//START: 2013 - PHASE 3 : PROJECT 4: AZ
				Item item = new Item(u.getItemCode(), u.getDescription(), u.getUnitOfMeasurement(),u.getClassification(),
						u.getSubClassification(),u.getIsVattable());
				//END: 2013 - PHASE 3 : PROJECT 4: AZ
				item.setItemType("utensils");
				tempList.add(item);
		}
		
		iterator = ofcSupList.iterator();
		while(iterator.hasNext()) {
			OfficeSupplies os = (OfficeSupplies) iterator.next();
				//START: 2013 - PHASE 3 : PROJECT 4: AZ
				Item item = new Item(os.getItemCode(), os.getDescription(), os.getUnitOfMeasurement(),os.getClassification(), os.getSubClassification(),
						os.getIsVattable());
				//END: 2013 - PHASE 3 : PROJECT 4: AZ
				item.setItemType("ofcSup");
				tempList.add(item);
		}
		
		iterator = finList.iterator();
		while(iterator.hasNext()){
			FinishedGood finGood = (FinishedGood) iterator.next();
				//START: 2013 - PHASE 3 : PROJECT 4: MARK
				Item item = new Item(finGood.getProductCode(), finGood.getDescription(), finGood.getUnitOfMeasurement(),finGood.getClassification(),
						finGood.getSubClassification(),finGood.getIsVattable());
				//END: 2013 - PHASE 3 : PROJECT 4: MARK
				item.setItemType("finGood");
				tempList.add(item);
		}
		
		return tempList;
	}
	
	public List getStockStatusInBetweenMonthAndYear(String dateFrom, String dateTo, String className,
			Session session) {
		return inventoryDao.getStockStatusInBetweenMonthAndYear(dateFrom, dateTo, className, session);
	}

	public List<PurchaseOrderDetails> getRelatedOrders(String itemCode,List<String> ordersRelated) {
		// TODO Auto-generated method stub
		return inventoryDao.getRelatedOrders(itemCode,ordersRelated);
	}
	
	public Map<String, String> getRelatedReturnSlipIds(String itemCode,List<String> ordersRelated) {
		// TODO Auto-generated method stub
		return inventoryDao.getRelatedReturnSlipIds(itemCode,ordersRelated);
	}

}
