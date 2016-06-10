package com.example.xmlparserdemo;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLParser {
	public XMLParser() {
		// TODO Auto-generated constructor stub
	}
	
	public String getXMLfromURL(String url) {
		String xml=null;
		DefaultHttpClient httpclient=new DefaultHttpClient();
		HttpPost post=new HttpPost(url);
		
		HttpResponse response;
		try {
			response = httpclient.execute(post);
			HttpEntity entity=response.getEntity();
			xml=EntityUtils.toString(entity);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return xml;
	}
	
	public Document getDOMElement(String xml) {
		Document doc=null;
		DocumentBuilderFactory factury=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db=factury.newDocumentBuilder();
			InputSource is=new InputSource();
			is.setCharacterStream(new StringReader(xml));
			doc=db.parse(is);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doc;	
	}
	
	public final String getElementValue(Node node) {
		Node child;
		if(node !=null){
			if (node.hasChildNodes()) {
				for(child=node.getFirstChild();child!=null;child=child.getNextSibling()){
					if (child.getNodeType()==Node.TEXT_NODE) {
						return child.getNodeValue();
					}
				}
			}
		}
		return "";
	}
	
	public String getValue(Element elm,String str) {
		NodeList n=elm.getElementsByTagName(str);
		return this.getElementValue(n.item(0));
	}
}
