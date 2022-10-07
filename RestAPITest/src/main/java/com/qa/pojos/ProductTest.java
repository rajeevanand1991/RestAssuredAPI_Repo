package com.qa.pojos;

import java.util.Arrays;

import org.apache.juneau.html.HtmlSerializer;
import org.apache.juneau.json.JsonParser;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.apache.juneau.xml.XmlSerializer;

public class ProductTest {

	public static void main(String[] args) throws SerializeException, ParseException {
		//Serialization
		//Convert POJO to JSON
		JsonSerializer jsonSerializer = JsonSerializer.DEFAULT_READABLE;
		
		String[] sellerNames = {"Neon Enterprises", "ABC Software", "XYZ IT Solutions"};
		Product product = new Product("MacBookPro", 2000, "white", sellerNames);
		
		String json = jsonSerializer.serialize(product);
		System.out.println("Now POJO was converted to Json now" +json);
		
		//Convert POJO to XML
		XmlSerializer xmlSerializer = XmlSerializer.DEFAULT_NS_SQ_READABLE;
		String xml = xmlSerializer.serialize(product);
		System.out.println("Now POJO was converted to XML now" +xml);
		
		//Convert POJO to HTML
		HtmlSerializer htmlSerializer = HtmlSerializer.DEFAULT_SQ_READABLE;
		String html = htmlSerializer.serialize(product);
		System.out.println("Now POJO was converted to HTML now" +html);
		//also we can convert other file types like CSV file type, URL Encoding
		
		//DeSerialization
		//JSON to POJO
		JsonParser jsonParser = JsonParser.DEFAULT;
		String jsonVal= "{\r\n" + 
				"	\"color\": \"white\",\r\n" + 
				"	\"name\": \"MacBookPro\",\r\n" + 
				"	\"price\": 2000,\r\n" + 
				"	\"sellerNames\": [\r\n" + 
				"		\"Neon Enterprises\",\r\n" + 
				"		\"ABC Software\",\r\n" + 
				"		\"XYZ IT Solutions\"\r\n" + 
				"	]\r\n" + 
				"}";
		Product pro = jsonParser.parse(jsonVal, Product.class);
		System.out.println(pro.getColor());
		System.out.println(pro.getName());
		System.out.println(pro.getPrice());
		System.out.println(Arrays.toString(sellerNames));
		System.out.println(pro);
	}
}