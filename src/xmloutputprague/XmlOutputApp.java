/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmloutputprague;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//prague
public class XmlOutputApp {

    
    public static void main(String argv[]) throws TransformerException, SAXException, IOException, ParserConfigurationException {
       
        //mysqlCon();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("QCandidate_Attachments_template.xml");
        Element root = document.getDocumentElement();

        Collection<Server> servers = new ArrayList<Server>();
        servers.add(new Server());
        //int spreadsheetkey = 0;
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prague","root","root"); 
        Statement stmt = con.createStatement();
        //ResultSet rs = stmt.executeQuery("SELECT * FROM HUA_FILE_BASE64 where id between 1 and 5");
//        ResultSet rs = stmt.executeQuery("select atsc.ats_candidate_id,\n" +
//            "hf.hua_user_id,\n" +
//            "hf.hua_file_id,\n" +
//            "hf.hua_file_name,\n" +
//            "hf.hua_file_mimetype,\n" +
//            "hfb.base64_string,\n" +
//            "hfb.id\n" +
//            "from ats_candidate atsc,\n" +
//            "hua_file hf,\n" +
//            "hua_attachment ha,\n" +
//            "hua_file_base64 hfb\n" +
//            "where hf.hua_file_id = ha.hua_file_id\n" +
//            "and hf.hua_file_id = hfb.original_file_name\n" +
//            "and atsc.ats_candidate_id = hua_user_id\n" +
//            "and hfb.id between 5001 and 6000");
        
        ResultSet rs = stmt.executeQuery("select h.id, "
                + "h.original_file_name, "
                + "h.base64_string, "
                + "h.original_file_directory, "
                + "h.date_converted, "
                + "u.user_id, "
                + "uw.Workday_Candidate_ID \n" +
                "from hua_file_base64 h,\n" +
                "user_details u,\n" +
                "user_workday uw \n" +
                "where h.original_file_directory = u.directory\n" +
                "and u.user_id = uw.Candidate_GUID\n" +
                //"and h.id between 17854 and 19526;");
                "and h.id between 19525 and 27000;");
        //ArrayList<String> list= new ArrayList<String>();
        while(rs.next())
        {

            for (Server server : servers) {
                NodeList nodes = document.getElementsByTagName("RowData");

                // server elements
                Element newRow = document.createElement("Row");
                Attr attrRow = document.createAttribute("ss:AutoFitHeight");
                attrRow.setValue("0");
                newRow.setAttributeNode(attrRow);


                Element cell1 = document.createElement("Cell");
                cell1.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell1);
                
                Element data0 = document.createElement("Data");
                Attr attrData19 = document.createAttribute("ss:Type");
                attrData19.setValue("String");
                data0.setAttributeNode(attrData19);
                data0.appendChild(document.createTextNode(rs.getString("user_id")));
                cell1.appendChild(data0);

                Element cell2 = document.createElement("Cell");
                cell2.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell2);
                
                // Spreadsheet ID
                Element data1 = document.createElement("Data");
                Attr attrData = document.createAttribute("ss:Type");
                attrData.setValue("String");
                data1.setAttributeNode(attrData);
                //data1.appendChild(document.createTextNode(server.getSpreadSheetKey()));
                data1.appendChild(document.createTextNode(rs.getString("id")));
                //data1.appendChild(document.createTextNode("Ferrari 202"));
                cell2.appendChild(data1);

                Element cell3 = document.createElement("Cell");
                cell3.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell3);

                Element cell4 = document.createElement("Cell");
                cell4.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell4);
                
                // Candidate ID
                Element data2 = document.createElement("Data");
                Attr attrData2 = document.createAttribute("ss:Type");
                attrData2.setValue("String");
                data2.setAttributeNode(attrData2);
                data2.appendChild(document.createTextNode(rs.getString("Workday_Candidate_ID")));
                //data2.appendChild(document.createTextNode("Ferrari 202"));
                cell4.appendChild(data2);

                Element cell5 = document.createElement("Cell");
                cell5.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell5);

                Element cell6 = document.createElement("Cell");
                cell6.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell6);

                Element cell7 = document.createElement("Cell");
                cell7.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell7);

                Element cell8 = document.createElement("Cell");
                cell8.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell8);

                Element cell9 = document.createElement("Cell");
                cell9.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell9);

                Element cell10 = document.createElement("Cell");
                cell10.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell10);

                Element cell11 = document.createElement("Cell");
                cell11.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell11);

                Element cell12 = document.createElement("Cell");
                cell12.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell12);

                Element cell13 = document.createElement("Cell");
                cell13.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell13);
                
                // Original File Name
                Element data3 = document.createElement("Data");
                Attr attrData3 = document.createAttribute("ss:Type");
                attrData3.setValue("String");
                data3.setAttributeNode(attrData3);
                data3.appendChild(document.createTextNode(rs.getString("original_file_name")));
                //data1.appendChild(document.createTextNode("Ferrari 202"));
                cell13.appendChild(data3);

                Element cell14 = document.createElement("Cell");
                cell14.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell14);
                
                // Base64 string
                Element data4 = document.createElement("Data");
                Attr attrData4 = document.createAttribute("ss:Type");
                attrData4.setValue("String");
                data4.setAttributeNode(attrData4);
                data4.appendChild(document.createTextNode(rs.getString("base64_string")));
                //data1.appendChild(document.createTextNode("Ferrari 202"));
                cell14.appendChild(data4);

                Element cell15 = document.createElement("Cell");
                cell15.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell15);
                
                // MIME type
//                Element data5 = document.createElement("Data");
//                Attr attrData5 = document.createAttribute("ss:Type");
//                attrData5.setValue("String");
//                data5.setAttributeNode(attrData5);
//                data5.appendChild(document.createTextNode(rs.getString("Cell")));
//                //data1.appendChild(document.createTextNode("Ferrari 202"));
//                cell15.appendChild(data5);

                Element cell16 = document.createElement("Cell");
                cell16.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell16);

                Element cell17 = document.createElement("Cell");
                cell17.appendChild(document.createTextNode(server.getBlankCell()));
                newRow.appendChild(cell17);
                
                // OTHER DOCUMENTS
                Element data6 = document.createElement("Data");
                Attr attrData6 = document.createAttribute("ss:Type");
                attrData6.setValue("String");
                data6.setAttributeNode(attrData6);
                data6.appendChild(document.createTextNode(server.getDocumentCategory()));
                //data1.appendChild(document.createTextNode("Ferrari 202"));
                cell17.appendChild(data6);

                root.appendChild(newRow);
                nodes.item(0).getParentNode().insertBefore(newRow, nodes.item(0));
    
            }
        
//        list.add(rs.getString("id"));
//        list.add(rs.getString("original_file_name"));
//        list.add(rs.getString("base64_string"));
//        list.add(rs.getString("original_file_directory"));
//        list.add(rs.getString("date_converted"));
//            
//
//            String[] result = new String[list.size()];
//            result = list.toArray(result);
//
//            for(int i =0; i<result.length; i++){
//                System.out.println(result[i]);
//            }
        }
        
        con.close();
        }catch(SQLException e){
            e.printStackTrace();
            //logs.append("Error: " + e + "\n");
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
            //logs.append("Error: " + ex + "\n");
        }
        
        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult("PragueAttachmentTest2.xml");
        transformer.transform(source, result);
    }

    public static class Server {
        //public String getName() { return "foo"; }
        
        //public Integer getPort() { return 12345; }
        
        public String getBlankCell() {return "";}
        
        public String getcandidateID() {
            //mysqlCon();
            //String cancdidateID = rs.getString(1);
            return "6512919";
        }
        
        public String getSpreadSheetKey() {return "7818";}
        
        public String getoriginalFileName() {return "test.txt";}
        
        public String getDocumentCategory() {return "OTHER_DOCUMENTS";}
        
        public String getbase64() {return "UEsDBAoAAAAAAAAAIQBexjIMJwAAACcAAAAIAAAAbWltZXR5cGVhcHBsaWNhdGlvbi92bmQub2FzaXMub3BlbmRvY3VtZW50LnRleHRQSwMEFAAGAAgAAAAhAFk78cR6AQAAaQUAAAwAAABzZXR0aW5ncy54bWyMU8tugzAQvFfqPyDfwSG5JFZJbj01t";}
        
        public String getoriginalFilePath() {return "\\britehouse_docs\\documents\\00\\00\\00\\00\\00\\10";}
    }

   }

