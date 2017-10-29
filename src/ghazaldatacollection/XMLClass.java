/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghazaldatacollection;
import java.io.File;
import java.net.URISyntaxException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.*;
/**
 *
 * @author :D
 */
public class XMLClass {
    FileHandle fl = new FileHandle();
    
    public void createNewXML(String shayar, String title, String content, String fileName) throws ParserConfigurationException, TransformerConfigurationException, URISyntaxException, TransformerException{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        // root element
        Element root = doc.createElement("Ghazal");
        doc.appendChild(root);
        
        // sub root element
        Element shayarElement = doc.createElement("Shayar");
        Element titleElement = doc.createElement("Title");
        Element contentElement = doc.createElement("Content");
        
        // add text
        shayarElement.appendChild(doc.createTextNode(shayar));
        titleElement.appendChild(doc.createTextNode(title));
        contentElement.appendChild(doc.createTextNode(content));
        
        // append elements
        root.appendChild(shayarElement);
        root.appendChild(titleElement);
        root.appendChild(contentElement);
        
        // write content to XML file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        if(!fl.CheckForFolderInParentDirectory("YO"))
            fl.CreateSubFolder(fl.getPathToRunnable(), "YO");
        StreamResult result = new StreamResult(new File(fl.getPathToRunnable()+File.separator+"YO"+File.separator+fileName+".xml"));
        transformer.transform(source, result);
        System.out.println("NEW FILE "+ fileName);
    }
}