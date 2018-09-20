package com.bugfree.controller.es;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bugfree.common.es.core.ElasticsearchTemplates;
import com.bugfree.model.brand.Brand;
import com.bugfree.model.product.Product;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.repository.brand.BrandRepository;
import com.bugfree.repository.product.ProductRepository;
import com.bugfree.repository.supplier.SupplierRepository;
import com.bugfree.repository.supplier.product.SellerProductListingsRepository;
import com.bugfree.service.brand.BrandService;
import com.bugfree.service.product.ProductService;

@Controller
public class ElasticController {

	// ElasticsearchTemplates elasticsearchTemplates = new ElasticsearchTemplates();
	@Autowired ProductService productService;
	@Autowired BrandService brandService;
	@Autowired SupplierRepository supplierRepository;
	@Autowired SellerProductListingsRepository sellerProductListingsRepository;
	@Autowired BrandRepository brandRepository;
	@Autowired ProductRepository productRepo;
	
	/*class UpdateKey extends Thread{
		
		private String key=null;
		private UpdateKey(String key){
			this.key=key;
			this.start();
		}
		private void createKey(String q){
			//TransportClient client = (TransportClient) elasticsearchTemplates.est.getClient();
					TransportClient client =  (TransportClient) elasticsearchTemplates.client;
					System.out.println("********************method*************************");

				try {
					IndexRequest indexRequest = new IndexRequest("krenai", "search", "q"+q)
					        .source(XContentFactory.jsonBuilder()
					            .startObject()
					                .field("name", q)
					                
					            .endObject());
					
					UpdateRequest updateRequest = new UpdateRequest();
					updateRequest.index("krenai");
					updateRequest.type("search");
					updateRequest.id("q"+q);
					updateRequest.doc(XContentFactory.jsonBuilder()
					        .startObject()
					        .field("name", q)
			                .endObject()).upsert(indexRequest);
					
					
					UpdateResponse resp = client.update(updateRequest).get();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			
		}
		
		
		@Override
		public void run(){
			createKey(this.key);
		}
		 
		
	}*/
	
	/*@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchPage(HttpServletRequest request, Model model) {

		Map<String, SearchHit> searchMap = new LinkedHashMap<String, SearchHit>();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String, String>();
		String q=request.getParameter("q");
		//TransportClient client = (TransportClient) elasticsearchTemplates.est.getClient();
		TransportClient client =  (TransportClient) elasticsearchTemplates.client;
		
		QueryBuilder qb1 = QueryBuilders.wildcardQuery("name",q+"*").boost(1.8f);
		QueryBuilder qb2 = QueryBuilders.wildcardQuery("name","*"+q+"*").boost(1);
		QueryBuilder qb3 = QueryBuilders.queryStringQuery(q).boost(2.1f);
		QueryBuilder qb4 = QueryBuilders.fuzzyQuery("name", q).fuzziness(Fuzziness.TWO).boost(1.8f);
		QueryBuilder qb5 = QueryBuilders.commonTermsQuery("name", q);
		QueryBuilder qb6 = QueryBuilders.matchQuery("name", q);
		
		SearchRequestBuilder srb1 = client
			    .prepareSearch("krenai").setTypes("product").setQuery(qb1).setSize(50);
		SearchRequestBuilder srb2 = client
			    .prepareSearch("krenai").setTypes("product").setQuery(qb2).setSize(50);
		SearchRequestBuilder srb3 = client
			    .prepareSearch("krenai").setTypes("product").setQuery(qb3).setSize(50);
		SearchRequestBuilder srb4 = client
			    .prepareSearch("krenai").setTypes("product").addHighlightedField("name").setQuery(qb4).setSize(50);
		SearchRequestBuilder srb5 = client
			    .prepareSearch("krenai").setTypes("product").setQuery(qb5).setSize(50);
		SearchRequestBuilder srb6 = client
			    .prepareSearch("krenai").setTypes("product").setQuery(qb6).setSize(10);

		MultiSearchResponse sr = null;
	System.out.println("********************start*************************");
	new UpdateKey(q);
	System.out.println("********************start afetr*************************");
	sr = client.prepareMultiSearch()
				  .add(srb6)
				  .add(srb1)
				  .add(srb4)
				  .add(srb5)
		        .add(srb2)
		        .add(srb3)
		        .execute().actionGet();
	
	 	for (MultiSearchResponse.Item item : sr.getResponses()) {
		    SearchResponse response = item.getResponse();
		    for (SearchHit hit : response.getHits().getHits()) {
		    	if(searchMap.size()<50){
		    		if(!searchMap.containsKey(hit.getId())){
		    			searchMap.put(hit.getId(), hit);
		    		}
		    	}
		    	else{
		    		System.out.println("****************ealse***************");
		    		Iterator it = searchMap.entrySet().iterator();
				    while (it.hasNext()) {
				    	map = new HashMap<String, String>();
				        Map.Entry pair = (Map.Entry)it.next();
				        SearchHit hits = (SearchHit) pair.getValue();
				        map.put("name", replaceFirst(hits.getSource().get("name").toString(),q.split(" ")));
				        map.put("image", hits.getSource().get("image")+"");
				        map.put("type", hits.getType());
				        map.put("incomplete", hits.highlightFields().get("name")+"");
				        mapList.add(map);
				        it.remove(); // avoids a ConcurrentModificationException
				    }
				
		    	}
		    	
		    }
		   
		    
		}
	 	Iterator it = searchMap.entrySet().iterator();
	    while (it.hasNext()) {
	    	map = new HashMap<String, String>();
	        Map.Entry pair = (Map.Entry)it.next();
	        SearchHit hits = (SearchHit) pair.getValue();
	        map.put("name", hits.getSource().get("name").toString());
	        map.put("image", hits.getSource().get("image")+"");
	        map.put("id", hits.getId().substring(1)+"");
	        map.put("description", hits.getSource().get("description")+"");
	        map.put("type", hits.getType());
	        map.put("incomplete", hits.highlightFields().get("name")+"");
	        
	        mapList.add(map);
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    model.addAttribute("searchList",mapList);
	    model.addAttribute("q", q);
	 	return "search/search";
	}
	
	
	@RequestMapping(value = "/directory/testes", method = RequestMethod.GET)
	public @ResponseBody String aboutUs() {
		//elasticTest.abc();
		//TransportClient client = (TransportClient) elasticsearchTemplates.est.getClient();
		TransportClient client =  (TransportClient) elasticsearchTemplates.client;
		
//		client.prepareIndex("talks", "talk", "1").setSource("title", "test1").setRefresh(true).get();
//		client.prepareIndex("talks", "talk", "2").setSource("title", "test2").setRefresh(true).get();
//		client.prepareIndex("talks", "talk", "3").setSource("title", "test3").setRefresh(true).get();
//		client.prepareIndex("talks", "talk", "4").setSource("title", "test4").setRefresh(true).get();
//		SearchResponse searchResponse = client.prepareSearch("test").get();
//		ImmutableOpenMap<Object, Object> map =  searchResponse.getContext();
//		System.out.println("*******searchResponse*******"+searchResponse.getContext().size());
//		System.out.println("*******searchResponse1*******"+searchResponse.getContext().get("1"));
		
		SearchResponse allHits = client.prepareSearch("talks")
                .setQuery(QueryBuilders.matchAllQuery())
                .execute().actionGet();
		
		//client.prepare
		ElasticsearchTemplate esTemplate = new ElasticsearchTemplate(client);
		
		GetResponse response = client.prepareGet("bugfree", "employee", "1").get();
		
		System.out.println("*****************1****************"+response.getField("age"));
		System.out.println("*****************1****************"+response.getField("smit"));
		System.out.println("*****************2****************"+response.getFields().size());
		System.out.println("*****************3****************"+response.getHeaders().size());
		System.out.println("*****************4****************"+response.getHeaders());
		System.out.println("*****************5****************"+response.getSource().size());
		System.out.println("*****************6****************"+response.isSourceEmpty());
		System.out.println("*****************7****************"+response.getSourceAsString());
		System.out.println("*****************8****************"+response.toString());
		
		MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
			    .add("bugfree", "employee", "vikas")          
			    .get();

			for (MultiGetItemResponse itemResponse : multiGetItemResponses) { 
			    GetResponse respon = itemResponse.getResponse();
			    if (response.isExists()) {                      
			        String json = respon.getSourceAsString(); 
			        System.out.println("**********json***"+json);
			    }
			}
		//Configur configur = new Configur();
		//System.out.println("****************configrur*********"+configur.est);
		
//	    IndexQuery query = new IndexQueryBuilder().withIndexName("test").withType("doc").withSource("java").build();
//	    System.out.println("*****************query0****************"+query.getIndexName());
//	    String id = esTemplate.index(query);
		//IndexQuery query = new IndexQueryBuilder().withIndexName("talks").withId("/tmp").withObject(talk).build();
		SearchResponse search  = client.prepareSearch().setTypes("doc","employee")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.execute().actionGet();
		
		System.out.println("*****************search****************"+search.contextSize());
		System.out.println("*****************search****************"+search.getSuccessfulShards());
		System.out.println("*****************search****************"+search.getHeaders());
		System.out.println("*****************search****************"+search.getHits().getTotalHits());
		System.out.println("*****************search****************"+search.getHits().maxScore());
		System.out.println("*****************search profile****************"+search.getProfileResults().size());
		
		for (SearchHit hit : search.getHits()) {
			
			System.out.println("*******************************************************************");
			System.out.println("****************rank*****"+hit.getScore()+"****hit****"+hit.getSourceAsString());
	        //Handle the hit...
	    }
		
		
		QueryBuilder qb = QueryBuilders.queryStringQuery("yadav smi").fuzziness(Fuzziness.TWO);
		QueryBuilder qb1 = QueryBuilders.fuzzyQuery("last_name", "umr").fuzziness(Fuzziness.TWO);
//		QueryBuilders
		SearchResponse scrollResp = client.prepareSearch("bugfree","test")
		        .addSort(SortBuilders.scoreSort())
		       .setQuery(qb)
		       .addHighlightedField("last_name")
		        .setSize(100).execute().actionGet(); //100 hits per shard will be returned for each scroll
		//Scroll until no hits are returned
		//while (true) {

		    for (SearchHit hit : scrollResp.getHits().getHits()) {
		        //Handle the hit...
		    	System.out.println("*********1*******rank*****"+hit.getScore()+"****hit****"+hit.getSourceAsString());
		    	System.out.println("*********1.1*******rank*****"+hit.getType());
			       
		    }
		    //scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
		    //Break condition: No hits are returned
//		    if (scrollResp.getHits().getHits().length == 0) {
//		        break;
//		    }
		//}
		
		SearchResponse responseSearch = client.prepareSearch("test","bugfree")
				.setTypes("doc","employee")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.termQuery("first_name", "smit"))
				.setQuery(QueryBuilders.termQuery("last_name", "yadav"))
				.setQuery(QueryBuilders.termQuery("about", "love"))
				.setQuery(QueryBuilders.termQuery("intrests", "marketing"))
				.addHighlightedField("about")
				.setFrom(0).setSize(50).setExplain(true)
				.execute().actionGet();
		
		for (SearchHit hit : responseSearch.getHits().getHits()) {
	        //Handle the hit...
	    	System.out.println("*********2*******rank*****"+hit.getScore()+"****hit****"+hit.getSourceAsString());
		       
	    }
//		
//		System.out.println("*****************responseSearch****************"+responseSearch.getHits());
//	    
		
//	    String id = esTemplate.index(query);
//	        
//	    GetQuery getQuery = new GetQuery();
//	    getQuery.setId(id);
//	    Talk queriedObject = esTemplate.queryForObject(getQuery, Talk.class);
//	    System.out.println("*****************id****************"+id);
//	    System.out.println("*****************queriedObject****************"+queriedObject.getTitle());
//	    System.out.println("*****************queriedObject****************"+queriedObject.getDate());
	        
//	    GetQuery getQuery = new GetQuery();
//	    getQuery.setId(id);
	    //Talk queriedObject = esTemplate.queryForObject(getQuery, Talk.class);
		//assertThat(searchResponse.getHits().getTotalHits(), is(1L));
		return "success";

	}
	
	
	private void updateSupplierInd(){
		List<Supplier> supplierList = (List<Supplier>) supplierRepository.findAll();
		//TransportClient client = (TransportClient) elasticsearchTemplates.est.getClient();
				TransportClient client =  (TransportClient) elasticsearchTemplates.client;
				for(Supplier supplier : supplierList){
			try {
				IndexRequest indexRequest = new IndexRequest("krenai", "supplier", "s"+supplier.getSupplierId())
				        .source(XContentFactory.jsonBuilder()
				            .startObject()
				            .field("name", supplier.getFullName())
				            .field("store_name", supplier.getSupplierShop().getShopName())
				            .field("profile_image", supplier.getImagePath())
				            .field("bannerImage", supplier.getSupplierShop().getShopTheme())
				            .field("latitude", supplier.getAddress().getLatitude())
				            .field("longitude", supplier.getAddress().getLongitude())
				            .field("address", supplier.getAddress().getAddressLine1())
				            .field("google_address", supplier.getAddress().getGoogleAddress())
				            .field("store_type", supplier.getStoreType())
				            
			                
				            .endObject());
				
				UpdateRequest updateRequest = new UpdateRequest();
				updateRequest.index("krenai");
				updateRequest.type("supplier");
				updateRequest.id("s"+supplier.getSupplierId());
				updateRequest.doc(XContentFactory.jsonBuilder()
				        .startObject()
				        .field("name", supplier.getFullName())
			            .field("store_name", supplier.getSupplierShop().getShopName())
			            .field("profile_image", supplier.getImagePath())
			            .field("bannerImage", supplier.getSupplierShop().getShopTheme())
			            .field("latitude", supplier.getAddress().getLatitude())
			            .field("longitude", supplier.getAddress().getLongitude())
			            .field("address", supplier.getAddress().getAddressLine1())
			            .field("google_address", supplier.getAddress().getGoogleAddress())
			            .field("store_type", supplier.getStoreType())
			            
			            .endObject()).upsert(indexRequest);
				
				
				UpdateResponse resp = client.update(updateRequest).get();
				System.out.println("*************created**************"+resp.isCreated());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public String replaceFirst(String q,String... keywords){
		for (String a : keywords) {
			q=q.replaceFirst("(?i)"+a, "<span>"+a+"</span>");
		}
		System.out.println(q);
		return q;
	}
	

	@RequestMapping(value = "/directory/search", method = RequestMethod.GET)
	public @ResponseBody List<String> searchKey(HttpServletRequest request) {
		//elasticTest.abc();
		//updateProdInd();
		//updateBrandInd();
		//TransportClient client = (TransportClient) elasticsearchTemplates.est.getClient();
				TransportClient client =  (TransportClient) elasticsearchTemplates.client;
				QueryBuilder qb = QueryBuilders.queryStringQuery(request.getParameter("q")).fuzziness(Fuzziness.TWO);
		QueryBuilder qb1 = QueryBuilders.fuzzyQuery("name", request.getParameter("q")).fuzziness(Fuzziness.TWO);
//		QueryBuilders
		SearchResponse scrollResp = client.prepareSearch("bugfree")
		        .addSort(SortBuilders.scoreSort())
		       .setQuery(qb)
		       .addHighlightedField("last_name")
		        .setSize(100).execute().actionGet(); //100 hits per shard will be returned for each scroll
		
		List<String> stringList = new ArrayList<String>();
		 for (SearchHit hit : scrollResp.getHits().getHits()) {
		        //Handle the hit...
//		    	System.out.println("*********1*******rank*****"+hit.getScore()+"****hit****"+hit.getSourceAsString());
//		    	System.out.println("*********1.1*******tyoe*****"+hit.getType());
//		    	String name = hit.getSource().get("name")+"";
//		    	System.out.println("*********1.1*******name*****"+name);
		    	//stringList.add(name);
		    	
		    }
		 SearchRequestBuilder srb1 = client
				    .prepareSearch().setQuery(qb);
				SearchRequestBuilder srb2 = client
				    .prepareSearch().setQuery(qb1);

		 
		MultiSearchResponse sr = client.prepareMultiSearch()
        .add(srb1)
        .add(srb2)
        .execute().actionGet();

		// You will get all individual responses from MultiSearchResponse#getResponses()
		long nbHits = 0;
		for (MultiSearchResponse.Item item : sr.getResponses()) {
		    SearchResponse response = item.getResponse();
		    nbHits += response.getHits().getTotalHits();
		    for (SearchHit hit : response.getHits().getHits()) {
		        //Handle the hit...
		    	String name = hit.getSource().get("name")+"";
		    	stringList.add(name);
		    	
		    }
		}
		  
		return stringList;
	}
	
	
	
	@RequestMapping(value = "/directory/search/sug", method = RequestMethod.GET)
	public @ResponseBody List<Map<String,String>> searchKeySuggestions(HttpServletRequest request) {
	
		Map<String, SearchHit> searchMap = new LinkedHashMap<String, SearchHit>();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String, String>();
		String q=request.getParameter("q");
		//TransportClient client = (TransportClient) elasticsearchTemplates.est.getClient();
		TransportClient client =  (TransportClient) elasticsearchTemplates.client;
		
		QueryBuilder qb1 = QueryBuilders.wildcardQuery("name",q+"*").boost(1.8f);
		QueryBuilder qb2 = QueryBuilders.wildcardQuery("name","*"+q+"*").boost(1);
		QueryBuilder qb3 = QueryBuilders.queryStringQuery(q).boost(2.1f);
		QueryBuilder qb4 = QueryBuilders.fuzzyQuery("name", q).fuzziness(Fuzziness.TWO).boost(1.8f);
		QueryBuilder qb5 = QueryBuilders.commonTermsQuery("name", q);
		QueryBuilder qb6 = QueryBuilders.matchQuery("name", q);
		
		SearchRequestBuilder srb1 = client
			    .prepareSearch("krenai").setTypes("product","brand").setQuery(qb1).setSize(10);
		SearchRequestBuilder srb2 = client
			    .prepareSearch("krenai").setTypes("product","brand").setQuery(qb2).setSize(10);
		SearchRequestBuilder srb3 = client
			    .prepareSearch("krenai").setTypes("product","brand").setQuery(qb3).setSize(10);
		SearchRequestBuilder srb4 = client
			    .prepareSearch("krenai").setTypes("product","brand").addHighlightedField("name").setQuery(qb4).setSize(10);
		SearchRequestBuilder srb5 = client
			    .prepareSearch("krenai").setTypes("product","brand").setQuery(qb5).setSize(5);
		SearchRequestBuilder srb6 = client
			    .prepareSearch("krenai").setTypes("product","brand").setQuery(qb6).setSize(3);

		MultiSearchResponse sr = null;
	if(q.length()>2){
		  sr = client.prepareMultiSearch()
				  .add(srb6)
				  .add(srb1)
				  .add(srb4)
				  .add(srb5)
		        .add(srb2)
		        .add(srb3)
		        .execute().actionGet();
	}
	 	for (MultiSearchResponse.Item item : sr.getResponses()) {
		    SearchResponse response = item.getResponse();
		    //System.out.println("******************total size********"+response.getHits().getTotalHits());
		    for (SearchHit hit : response.getHits().getHits()) {
		    	if(searchMap.size()<10){
		    		if(!searchMap.containsKey(hit.getId())){
		    			searchMap.put(hit.getId(), hit);
//		    		System.out.println("****************added now size***************"+searchMap.size());
//		    		System.out.println("*********1*******rank*--->"+hit.getScore()+"****hit****"+hit.getSource().get("name"));
		    		}
		    	}
		    	else{
		    		Iterator it = searchMap.entrySet().iterator();
				    while (it.hasNext()) {
				    	map = new HashMap<String, String>();
				        Map.Entry pair = (Map.Entry)it.next();
				        SearchHit hits = (SearchHit) pair.getValue();
				        map.put("name", hits.getSource().get("name").toString());
				        map.put("url",hits.getSource().get("name").toString());
				        map.put("image", hits.getSource().get("image")+"");
				        map.put("type", hits.getType());
				        map.put("incomplete", hits.highlightFields().get("name")+"");
//				        System.out.println("***********highlightFields*********"+hit.highlightFields().get("name"));
//				        System.out.println("***********highlightField*********"+hit.highlightFields().keySet().size());
						    
				        mapList.add(map);
//				        System.out.println("******pair*******"+pair.getKey() + " = " + pair.getValue());
				        it.remove(); // avoids a ConcurrentModificationException
				    }
				
				    return mapList;
		    		//System.out.println("*********searchmap***********"+searchMap.size());
			    	//break;
		    	}
//		    	System.out.println("****************rank************"+hit.getScore()+"****hit****"+hit.getSource().get("name"));
		    	//Handle the hit...
		    	//System.out.println("*********2*****"+hit.getScore()+"****hit****"+hit.getSourceAsString());
		    	//System.out.println("*********searchmap***********"+searchMap.size());
		    	//System.out.println("*********1.1*******tyoe*****"+hit.getType());
		    	//String name = hit.getSource().get("name")+"";
		    	//System.out.println("*********1.1*******name*****"+name);
		    	//stringList.add(hit.getSource().get("name")+"");
		    	
		    }
		   
		    
		}
	 	Iterator it = searchMap.entrySet().iterator();
	    while (it.hasNext()) {
	    	map = new HashMap<String, String>();
	        Map.Entry pair = (Map.Entry)it.next();
	        SearchHit hits = (SearchHit) pair.getValue();
	        //map.put("name", replaceFirst(hits.getSource().get("name").toString(),q.split(" ")));
	        map.put("name", hits.getSource().get("name").toString());
	        map.put("url",hits.getSource().get("name").toString());
	        map.put("image", hits.getSource().get("image")+"");
	        map.put("type", hits.getType());
	        map.put("incomplete", hits.highlightFields().get("name")+"");
//	        System.out.println("***********highlightFields*********"+hit.highlightFields().get("name"));
//	        System.out.println("***********highlightField*********"+hit.highlightFields().keySet().size());
			    
	        mapList.add(map);
//	        System.out.println("******pair*******"+pair.getKey() + " = " + pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	 	 //System.out.println("*********searchmap1***********"+searchMap.size());
	 	return mapList;	
	}
	
	

	@RequestMapping(value = "/directory/getlist", method = RequestMethod.GET)
	public @ResponseBody List<Map<String,String>> getProductList(HttpServletRequest request) {
	
		Supplier supplier = supplierRepository.findByEmailId((String)SecurityUtils.getSubject().getPrincipal());
		Map<String, SearchHit> searchMap = new LinkedHashMap<String, SearchHit>();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		Set<Integer> set = new HashSet<Integer>();
		Map<String,String> map = null;
		String q=request.getParameter("q");
		//TransportClient client = (TransportClient) elasticsearchTemplates.est.getClient();
		TransportClient client =  (TransportClient) elasticsearchTemplates.client;
		
		QueryBuilder qb1 = QueryBuilders.wildcardQuery("name",q+"*").boost(1.8f);
		QueryBuilder qb2 = QueryBuilders.wildcardQuery("name","*"+q+"*").boost(1);
		QueryBuilder qb3 = QueryBuilders.queryStringQuery(q).boost(2.1f);
		QueryBuilder qb4 = QueryBuilders.fuzzyQuery("name", q).fuzziness(Fuzziness.TWO).boost(1.8f);
		QueryBuilder qb5 = QueryBuilders.commonTermsQuery("name", q);
		QueryBuilder qb6 = QueryBuilders.matchQuery("name", q);
		
		SearchRequestBuilder srb1 = client
			    .prepareSearch("krenai").setTypes("product").setQuery(qb1).setSize(50);
		SearchRequestBuilder srb2 = client
			    .prepareSearch("krenai").setTypes("product").setQuery(qb2).setSize(50);
		SearchRequestBuilder srb3 = client
			    .prepareSearch("krenai").setTypes("product").setQuery(qb3).setSize(50);
		SearchRequestBuilder srb4 = client
			    .prepareSearch("krenai").setTypes("product").addHighlightedField("name").setQuery(qb4).setSize(50);
		SearchRequestBuilder srb5 = client
			    .prepareSearch("krenai").setTypes("product").setQuery(qb5).setSize(50);
		SearchRequestBuilder srb6 = client
			    .prepareSearch("krenai").setTypes("product").setQuery(qb6).setSize(10);

		MultiSearchResponse sr = null;
	System.out.println("********************start*************************");
	new UpdateKey(q);
	System.out.println("********************start afetr*************************");
	sr = client.prepareMultiSearch()
				  .add(srb6)
				  .add(srb1)
				  .add(srb4)
				  .add(srb5)
		        .add(srb2)
		        .add(srb3)
		        .execute().actionGet();
	
	 	for (MultiSearchResponse.Item item : sr.getResponses()) {
		    SearchResponse response = item.getResponse();
		    for (SearchHit hit : response.getHits().getHits()) {
		    	if(searchMap.size()<50){
		    		if(!searchMap.containsKey(hit.getId())){
		    			searchMap.put(hit.getId(), hit);
		    		}
		    	}
		    	else{
		    		System.out.println("****************ealse***************");
		    		Iterator it = searchMap.entrySet().iterator();
				    while (it.hasNext()) {
				    	map = new HashMap<String, String>();
				        Map.Entry pair = (Map.Entry)it.next();
				        SearchHit hits = (SearchHit) pair.getValue();
				        map.put("name", replaceFirst(hits.getSource().get("name").toString(),q.split(" ")));
				        map.put("image", hits.getSource().get("image")+"");
				        map.put("type", hits.getType());
				        map.put("incomplete", hits.highlightFields().get("name")+"");
				        mapList.add(map);
				        it.remove(); // avoids a ConcurrentModificationException
				    }
				
		    	}
		    	
		    }
		   
		    
		}
	 	Iterator it = searchMap.entrySet().iterator();
	    while (it.hasNext()) {
	    	
	        Map.Entry pair = (Map.Entry)it.next();
	        SearchHit hits = (SearchHit) pair.getValue();
	        set.add(Integer.parseInt(hits.getId().substring(1)));
	        it.remove(); // avoids a ConcurrentModificationException
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
	
	
	
	private void updateProdInd(){
		//TransportClient client = (TransportClient) elasticsearchTemplates.est.getClient();
				TransportClient client =  (TransportClient) elasticsearchTemplates.client;
				List<Product> productList = (List<Product>) productRepo.findAll();
		IndexResponse response = null;
		for(Product product : productList){

			try {
				
				IndexRequest indexRequest = new IndexRequest("krenai", "brand", "p"+product.getProductId())
				        .source(XContentFactory.jsonBuilder()
				            .startObject()
				            .field("name", product.getProductName())
	                        .field("image", product.getProductImage().getImagePath1())
	                        .field("category", product.getCategory().getCategoryName())
	                        .field("subcategory", product.getSubCategory().getSubCategoryName())
	                        .field("description", product.getProductDescription().getDescription())
	                        .field("brand",product.getBrand().getBrandName())
				            .endObject());
				
				UpdateRequest updateRequest = new UpdateRequest();
				updateRequest.index("krenai");
				updateRequest.type("product");
				updateRequest.id("p"+product.getProductId());
				updateRequest.doc(XContentFactory.jsonBuilder()
				        .startObject()
				        .field("name", product.getProductName())
                        .field("image", product.getProductImage().getImagePath1())
                        .field("category", product.getCategory().getCategoryName())
                        .field("subcategory", product.getSubCategory().getSubCategoryName())
                        .field("description", product.getProductDescription().getDescription())
                        .field("brand",product.getBrand().getBrandName())
				        .endObject()).upsert(indexRequest);
				UpdateResponse resp = client.update(updateRequest).get();
				
				System.out.println("*************created**************"+resp.isCreated());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void updateBrandInd(){
		List<Brand> brandList = (List<Brand>) brandRepository.findAll();
		//TransportClient client = (TransportClient) elasticsearchTemplates.est.getClient();
				TransportClient client =  (TransportClient) elasticsearchTemplates.client;
				for(Brand brand : brandList){
			try {
				IndexRequest indexRequest = new IndexRequest("krenai", "brand", "b"+brand.getBrandId())
				        .source(XContentFactory.jsonBuilder()
				            .startObject()
				            .field("name", brand.getBrandName())
			                
				            .endObject());
				
				UpdateRequest updateRequest = new UpdateRequest();
				updateRequest.index("krenai");
				updateRequest.type("brand");
				updateRequest.id("b"+brand.getBrandId());
				updateRequest.doc(XContentFactory.jsonBuilder()
				        .startObject()
				        .field("name", brand.getBrandName())
		                .endObject()).upsert(indexRequest);
				
				
				UpdateResponse resp = client.update(updateRequest).get();
				System.out.println("*************created**************"+resp.isCreated());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	private void updateSuppInd(){
		List<Supplier> supplierList = (List<Supplier>) supplierRepository.findAll();
		//TransportClient client = (TransportClient) elasticsearchTemplates.est.getClient();
				TransportClient client =  (TransportClient) elasticsearchTemplates.client;
				for(Supplier supplier : supplierList){
			try {
				IndexRequest indexRequest = new IndexRequest("krenai", "supplier", "s"+supplier.getSupplierId())
				        .source(XContentFactory.jsonBuilder()
				            .startObject()
				            .field("name", supplier.getFullName())
				            .field("storename", supplier.getSupplierShop().getShopName())
				            .field("address", supplier.getAddress().getGoogleAddress())
				            .field("contact", supplier.getContactNo())
				            .field("profileimage", supplier.getImagePath())
				            .field("location", supplier.getAddress().getLatitude()+", "+supplier.getAddress().getLongitude())
				            .field("opentime", supplier.getSupplierShop().getShopOpeningTime())
				            .field("closetime", supplier.getSupplierShop().getShopClosingTime())
				            .field("storetype", supplier.getStoreType())
			                
				            .endObject());
				
				UpdateRequest updateRequest = new UpdateRequest();
				updateRequest.index("krenai");
				updateRequest.type("supplier");
				updateRequest.id("s"+supplier.getSupplierId());
				updateRequest.doc(XContentFactory.jsonBuilder()
				        .startObject()
				        .field("name", supplier.getFullName())
				        .field("storename", supplier.getSupplierShop().getShopName())
			            .field("address", supplier.getAddress().getGoogleAddress())
			            .field("contact", supplier.getContactNo())
			            .field("profileimage", supplier.getImagePath())
			            .field("location", supplier.getAddress().getLatitude()+", "+supplier.getAddress().getLongitude())
			            .field("opentime", supplier.getSupplierShop().getShopOpeningTime())
			            .field("closetime", supplier.getSupplierShop().getShopClosingTime())
			            .field("storetype", supplier.getStoreType())
		                .endObject()).upsert(indexRequest);
				
				
				UpdateResponse resp = client.update(updateRequest).get();
				System.out.println("*************created**************"+resp.isCreated());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	
	@RequestMapping(value = "/directory/update/product/brand", method = RequestMethod.GET)
	public @ResponseBody String updateProBra(HttpServletRequest request) {
		updateProdInd();
		updateBrandInd();
		updateSuppInd();
		return "success";
	}*/
	
}
