package com.bugfree.service.encrypt;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.service.supplier.SupplierService;

@Service
public class EncryptService {

	@Autowired
	private SupplierService supplierService;
	
	//Get Salt in ByteSource Type
    public static ByteSource getSaltInByteSource()
    {
        //Always use a SecureRandom generator
    	RandomNumberGenerator randomGenerator = new SecureRandomNumberGenerator();
        //Create array for salt
    	ByteSource salt = randomGenerator.nextBytes();
        //return salt
        return salt;
    }
    
    public String generateSecurePasswordInHex(String passwordToHash, ByteSource salt, int iterations_of_hashing) 
    {
    	return new Sha512Hash(passwordToHash, salt, iterations_of_hashing).toHex();
    }
    
    
    public boolean verifyOldPassword(Supplier supplier, String oldPassword, String newPassword){
    	
    	String oldPasswordHex = supplier.getPassword();
    	ByteSource bs =  new SimpleByteSource(Hex.decode(supplier.getSalt()));
    	String password = generateSecurePasswordInHex(oldPassword, bs, 10000);
    	if(oldPasswordHex.equals(password)){
    		System.out.println("*****************old passwords matches successfully*********");
    		ByteSource salt = getSaltInByteSource();
        	password = generateSecurePasswordInHex(newPassword, salt, 10000);
        	System.out.println("newPassword is: "+newPassword);
    		supplier.setPassword(password);
    		supplier.setSalt(salt.toHex());
    		supplierService.save(supplier);
    		return true;
    	}
    	else{
    		System.out.println("***************new salt :"+bs.toHex());
    		System.out.println("*****************old passwords does not matches successfully*********");
    		return false;
    	}
    	
    }
    
}
