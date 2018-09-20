package com.bugfree.controller.ElasticAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bugfree.common.KrenaiCONSTANTS;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.repository.brand.BrandRepository;
import com.bugfree.repository.product.ProductRepository;
import com.bugfree.repository.supplier.SupplierRepository;
import com.bugfree.repository.supplier.product.SellerProductListingsRepository;
import com.bugfree.service.brand.BrandService;
import com.bugfree.service.product.ProductService;

@Controller
public class ElasticAPIController {

	@Autowired ProductService productService;
	@Autowired BrandService brandService;
	@Autowired SupplierRepository supplierRepository;
	@Autowired SellerProductListingsRepository sellerProductListingsRepository;
	@Autowired BrandRepository brandRepository;
	@Autowired ProductRepository productRepo;
	
	
	URL myURL=null;
    BufferedReader reader=null;
    URLConnection myURLConnection = null ;
    String encodedUrl = null;


	
	@RequestMapping(value = "directory/search/sug", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, String>> searchKeySuggestions(HttpServletRequest request) {
	
	String q = request.getParameter("q");
	List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
	Map<String,String> map = new HashMap<String, String>();
	
		try {
		    encodedUrl = URLEncoder.encode(q, "UTF-8");
		} catch (UnsupportedEncodingException ignored) {
		    // Can be safely ignored because UTF-8 is always supported
		}
		
	    try{
	    	//myURL = new URL("http://123.63.33.43/blank/sms/user/urlsmstemp.php?username=bhuna1&pass=123456&senderid=SNSKAR&dest_mobileno="+contact+"&message="+encodedUrl+"");
	    	myURL = new URL(KrenaiCONSTANTS.userApplication+"/directory/search/sug?q="+encodedUrl);
	    	myURLConnection = myURL.openConnection();
		    myURLConnection.connect();
	    	reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		    //reading response
	    	JSONArray array = null;
	    	String response;
	    	while ((response = reader.readLine()) != null){
		    	//System.out.println("======="+response);
		    	 array = new JSONArray(response);
		    	for(int i=0; i<array.length(); i++){
		    		JSONObject obj = array.getJSONObject(i);
		    		map = new HashMap<String, String>();
			        map.put("name", obj.getString("name"));
			        map.put("url",obj.getString("url"));
			        map.put("image", obj.getString("image"));
			        map.put("type", obj.getString("type"));
			        System.out.println("==============="+obj);
			        mapList.add(map);
		    	}
		    }
		    reader.close();
		    
		    return mapList;
		    
	    }catch(Exception e){
	    	
	    }
	    return mapList;
	}
	
	

	@RequestMapping(value = "/directory/getlist", method = RequestMethod.GET)
	public @ResponseBody List<Map<String,String>> getProductList(HttpServletRequest request) {
	
		Supplier supplier = supplierRepository.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Set<Integer> set = new HashSet<Integer>();
		Map<String,String> map = null;
		String q=request.getParameter("q");
		//TransportClient client = (TransportClient) elasticsearchTemplates.est.getClient();
		
		try {
		    encodedUrl = URLEncoder.encode(q, "UTF-8");
		} catch (UnsupportedEncodingException ignored) {
		    // Can be safely ignored because UTF-8 is always supported
		}
		
	    try{
	    	myURL = new URL(KrenaiCONSTANTS.userApplication+"/directory/getlist?q="+encodedUrl);
	    	myURLConnection = myURL.openConnection();
		    myURLConnection.connect();
	    	reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		    //reading response
	    	JSONArray array = null;
	    	String response;
	    	
	    	while ((response = reader.readLine()) != null){
	    		System.out.println("==res====="+response);
		    	 array = new JSONArray(response);
		    	for(int i=0; i<array.length(); i++){
		    		JSONObject obj = array.getJSONObject(i);
		    		set.add(Integer.parseInt(obj.getString("id")));
		    		System.out.println("==============="+obj);
		    	}
		    }
	    	
		    reader.close();
		    
		   // return mapList;
		    
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    List<Object> productList = sellerProductListingsRepository.findSearchReasultsWithSplExisting(supplier.getSupplierId(), set);
	    System.out.println("*********productList********"+productList.size());
	    for(Object object : productList){
	    	Object[] objArray = (Object[]) object;
	    	map = new HashMap<String, String>();
	    	map.put("productId", String.valueOf(objArray[0]));
	    	map.put("productName", String.valueOf(objArray[1]));
	    	map.put("packagedQty", String.valueOf(objArray[2]));
	    	map.put("packagedUnit", String.valueOf(objArray[3]));
	    	map.put("brand", String.valueOf(objArray[4]));
	    	map.put("image", String.valueOf(objArray[5]));
	    	map.put("spl", String.valueOf(objArray[6]));
	    	map.put("category", String.valueOf(objArray[7]));
	    	map.put("subcategory", String.valueOf(objArray[8]));
	    	mapList.add(map);
	    }
	    
	 	return mapList;	
	}
	
	
	

	
}
