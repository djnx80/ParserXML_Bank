package com.djnx.xml;

import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

// odczytaj dane z pliku XML umieszczonego na stronie
public class DOMParser {

	public static void main(String[] args) {
		try {
			// stwórz nowe instancje
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			// odczytaj plik z url
			URL url = new URL("http://www.nbp.pl/kursy/xml/LastA.xml");
			Document doc = dBuilder.parse(new InputSource(url.openStream()));
			doc.getDocumentElement().normalize();

//			System.out.println("Korzeñ: " + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("pozycja");
			// szukana waluta
			String walutaEUR = "EUR";

			// przejdz ca³¹ listê
			for (int temp = 0; temp < nList.getLength(); temp++) {
				// pobierz pojedynczy element
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					// pobierz wartoœæ z tagu kod_waluty i kurs_sredni
					String waluta = eElement.getElementsByTagName("kod_waluty").item(0).getTextContent();
					String kurs = eElement.getElementsByTagName("kurs_sredni").item(0).getTextContent();
					// czy szukana waluta? jak tak to wyœwietl
					if (waluta.equals(walutaEUR)) {
						System.out.println("Waluta: " + waluta);
						System.out.println("  Kurs: " + kurs);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}