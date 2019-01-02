package com.niit.ZShopBackend.DAO;

import java.util.List;

import com.niit.ZShopBackend.model.Supplier;

public interface SupplierDAO {
	
	public boolean saveSupplier(Supplier supplier);
	public boolean updateSupplier(Supplier supplier);
	public boolean deleteSupplier(Supplier supplier);
	public Supplier getSupplier(int supplierId);
	public List<Supplier> listSuppliers();
	

}
