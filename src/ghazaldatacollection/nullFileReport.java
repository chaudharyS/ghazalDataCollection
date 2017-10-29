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
 * @author Poopy_Babez
 */
public class nullFileReport {
    FileHandle fl = new FileHandle();
    public void createNewXML(String ns) throws ParserConfigurationException, TransformerConfigurationException, URISyntaxException, TransformerException{
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    Document doc = docBuilder.newDocument();
    // root element
    Element root = doc.createElement("URL");
    doc.appendChild(root);
    root.appendChild(doc.createTextNode(ns));
    // write content to XML file
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    if(!fl.CheckForFolderInParentDirectory("YO"))
            fl.CreateSubFolder(fl.getPathToRunnable(), "YO");
        StreamResult result = new StreamResult(new File(fl.getPathToRunnable()+File.separator+"YO"+File.separator+"URL.xml"));
        transformer.transform(source, result);
    }
}
