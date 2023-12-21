package com.ceva.section25.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class DOMTest1 {
    // obtenemos la info de subnodo
    private static String textContent(Node node) {
        NodeList children = node.getChildNodes();
        StringBuilder sb = new StringBuilder();
        for (int n=0; n<children.getLength(); n++) {
            // validamos si es un nodo tipo text
            if (children.item(n).getNodeType() == Node.TEXT_NODE)
                sb.append(children.item(n).getTextContent());
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        // 1.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // 2.
        DocumentBuilder db = dbf.newDocumentBuilder();
        // 3.
        Document doc = db.parse(DOMTest1.class.getResourceAsStream("/com/ceva/section25/xml/databaseConn.xml"));

        System.out.println(doc.getNodeName()); // print #document
        System.out.println(doc.getNodeValue()); // print null

        NodeList nodes = doc.getChildNodes();
        // print: document has 1 node(s) - representa al nodo connectionList
        System.out.println("Document has " + nodes.getLength() + " node(s).");

        // buscamos al nodo connectionList
        Node connectionListNode = null;
        for (int n=0; n<nodes.getLength(); n++) {
            Node node = nodes.item(n);
            System.out.println("node: " + node.getNodeName());
            if (node.getNodeName().equals("connectionList"))
                connectionListNode = node;
        }
        if (connectionListNode != null) {
            System.out.println("connectionList found.\n");
            nodes = connectionListNode.getChildNodes();
            // recorremos todos los nodos de connectionList
            for (int n=0; n<nodes.getLength(); n++) {
                Node node = nodes.item(n);
                System.out.println("node: " + node.getNodeName());
                // preguntamos por el nodo connection
                if (node.getNodeName().equals("connection")) {
                    // obtenemos el nodo
                    Node nodeName = node.getAttributes().getNamedItem("name");
                    // obtenemos el atributo name del nodo
                    System.out.println("connection name: " + nodeName.getNodeValue());

                    // recorremos los subnodos de connection
                    NodeList subNodes = node.getChildNodes();
                    for (int n2=0; n2<subNodes.getLength(); n2++) {
                        Node subNode = subNodes.item(n2);
                        if (subNode.getNodeName().equals("url")) {
                            System.out.println("url: " + textContent(subNode));
                        } else if (subNode.getNodeName().equals("driver")) {
                            System.out.println("driver: " + textContent(subNode));
                        } else
                            System.out.println("    subNode: " + subNode.getNodeName());
                    }
                }
            }
        }
    }
}
