package xmlLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLNode {

	private String name ;
	private String data ;
	private Map<String, String> attribute ;
	private Map<String, List<XMLNode>> children ;
	
	public XMLNode(String name) {
		this.name = name ;
	}
	
	public void addAttribute(String name, String data) {
		if(attribute == null) {
			attribute = new HashMap<String, String>();
		}
		attribute.put(name, data) ;
	}
	
	public void addChild(XMLNode node) {
		if(children == null) {
			children = new HashMap<String, List<XMLNode>>();
		}
		List<XMLNode> childlist = children.get(node.getName()) ;
		if(childlist == null) {
			childlist = new ArrayList<XMLNode>();
		}
		childlist.add(node) ;
		children.put(node.getName(), childlist) ;
	}
	public String getAttribute(String name) {
		if(attribute != null) {
			String data = attribute.get(name) ;
			if(data != null) {
				return data ;
			}
		}
		return "";
	}
	public List<XMLNode> getChildren(String name) {
		return children.get(name) ;
	}

	public String getName() {
		return name ;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}
	
}
