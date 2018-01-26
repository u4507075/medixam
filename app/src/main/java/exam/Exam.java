/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import com.example.piyapong.drawing.Variable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author bon
 */
public class Exam {
    String examtype="";
    String examtitle="";
    String shuffle="";
    String total="";
    String com="";
    String studentid="";
    String studentname="";
    String set="";
    String row="";
    ArrayList survey;

    HashMap<Integer,Examination> exammap = new HashMap<>();
    //ArrayList<Examination> examlist = new ArrayList();
    
    public void parse(String string) throws ParserConfigurationException, SAXException, IOException
    {
        ByteArrayInputStream stream = new ByteArrayInputStream(string.getBytes("UTF8"));
        DocumentBuilder  builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(stream); 
        doc.getDocumentElement().normalize();
        
        NodeList examination = doc.getElementsByTagName("examination");
        Variable.EXAMTITLE = getAttributevalue(examination, 0, "examtitle");
        Variable.EXAMTIME = getAttributevalue(examination, 0, "examtime");
        Variable.STUDENTID = getAttributevalue(examination, 0, "studentid");
        Variable.STUDENTNAME = getAttributevalue(examination, 0, "studentname");
        /*
        examtype = getNodevalue(getSubnode(examination, 0, "examtype"));
        examtitle = getNodevalue(getSubnode(examination, 0, "examtitle"));
        shuffle = getNodevalue(getSubnode(examination, 0, "shuffle"));
        total = getNodevalue(getSubnode(examination, 0, "total"));
        com = getNodevalue(getSubnode(examination, 0, "com"));
        studentid = getNodevalue(getSubnode(examination, 0, "studentid"));
        studentname = getNodevalue(getSubnode(examination, 0, "studentname"));
        set = getNodevalue(getSubnode(examination, 0, "set"));
        row = getNodevalue(getSubnode(examination, 0, "row"));
        
        NodeList surveynode = getSubnode(examination, 0, "survey");
        Element surveyelement = (Element)surveynode.item(0);
        NodeList surveytextnode = surveyelement.getElementsByTagName("text");
        
        for(int i=0;i<surveytextnode.getLength();i++)
        {
            String[] value = new String[2];
            Element element = (Element)surveytextnode.item(i);
            value[1] = element.getAttribute("type");
            value[0] = element.getFirstChild().getNodeValue();
            survey.add(value);
        }
        
        NodeList examlistnode = getSubnode(examination, 0, "examlist");
        Element examelement = (Element)examlistnode.item(0);
        NodeList examnode = examelement.getElementsByTagName("exam");
        */
        //Parse survey
        //Survey ss = new Survey(examination);
        //survey = ss.getSurvey();

        //Parse exam
        NodeList examnode = getSubnode(examination, 0, "exam");

        for(int i=0;i<examnode.getLength();i++)
        {
            Examination ex = new Examination();
            Element element = (Element)examnode.item(i);
            ex.setColumn(element.getAttribute("column"));
            ex.setExamid(getNodevalue(element.getElementsByTagName("examid")));
            ex.setExamtype(getNodevalue(element.getElementsByTagName("examtype")));
            ex.setCorrect(getNodevalue(element.getElementsByTagName("correct")));
            ex.setSelectedanswer(getNodevalue(element.getElementsByTagName("selectedanswer")));
            ex.setTopiccode(getNodevalue(element.getElementsByTagName("topiccode")));
            ex.setExamlevel(getNodevalue(element.getElementsByTagName("examlevel")));
            ex.setMedcode(getNodevalue(element.getElementsByTagName("medcode")));
            ex.setTextcode(getNodevalue(element.getElementsByTagName("textcode")));
            ex.setReference(getNodevalue(element.getElementsByTagName("reference")));
            
            NodeList question = element.getElementsByTagName("question");
            Element questionelement = (Element)question.item(0);
            NodeList questionstem = questionelement.getElementsByTagName("stem");
            
            NodeList style = element.getElementsByTagName("style");
            NodeList questionstyle =getSubnode(style, 0, "question");
            Element questionstyleelement = (Element)questionstyle.item(0);
            NodeList questionstylestem = questionstyleelement.getElementsByTagName("stem");
            
            for(int j=0;j<questionstem.getLength();j++)
            {
                Element qelement = (Element)questionstem.item(j);
                String value = "";
                if(qelement.getFirstChild()!=null)
                {
                    value = qelement.getFirstChild().getNodeValue();
                }
                Question q = new Question(qelement.getAttribute("type"), value, qelement.getAttribute("width"), qelement.getAttribute("height"));
                ex.addQuestion(q);
                
                Element stemelement = (Element)questionstylestem.item(j);
                if(stemelement.getFirstChild()!=null)
                {
                    NodeList squestion = stemelement.getElementsByTagName("s");
                    for(int k=0;k<squestion.getLength();k++)
                    {
                        Element s = (Element)squestion.item(k);
                        if(s.getFirstChild()!=null)
                        {
                            s.getElementsByTagName("type");
                            String type = getNodevalue(s.getElementsByTagName("type"));
                            String start = getNodevalue(s.getElementsByTagName("start"));
                            String end = getNodevalue(s.getElementsByTagName("end"));
                            if(!type.equals("") && !start.equals("") && !end.equals(""))
                            {
                                int istart = Integer.parseInt(start);
                                int iend = Integer.parseInt(end);
                                for(int l=istart;l<iend;l++)
                                {
                                    q.addStyle(type, l);
                                }
                            }
                        }
                    }
                }
            }
            
            NodeList answer = element.getElementsByTagName("answer");
            Element answerelement = (Element)answer.item(0);
            NodeList choicenode = answerelement.getElementsByTagName("choice");
            
            NodeList answerstyle =getSubnode(style, 0, "answer");
            Element answerstyleelement = (Element)answerstyle.item(0);
            NodeList choicestylestem = answerstyleelement.getElementsByTagName("choice");
            
            for(int j=0;j<choicenode.getLength();j++)
            {
                Element celement = (Element)choicenode.item(j);
                Choice choice = new Choice(celement.getAttribute("id"));
                
                NodeList stem = celement.getElementsByTagName("stem");
                for(int k=0;k<stem.getLength();k++)
                {
                    Element stemelement = (Element)stem.item(k);
                    String value = "";
                    if(stemelement.getFirstChild()!=null)
                    {
                        value = stemelement.getFirstChild().getNodeValue();
                    }
                    choice.addStem(stemelement.getAttribute("type"), value);
                }
                ex.addChoice(choice);
                
                Element stemelement = (Element)choicestylestem.item(j);
                if(stemelement.getFirstChild()!=null)
                {
                    NodeList schoice = stemelement.getElementsByTagName("stem");
                    for(int l=0;l<schoice .getLength();l++)
                    {
                        Element cstem = (Element)schoice .item(l);
                        if(cstem.getFirstChild()!=null)
                        {
                                NodeList sschoice = cstem.getElementsByTagName("s");
                                for(int k=0;k<sschoice.getLength();k++)
                                {
                                    Element s = (Element)sschoice.item(k);
                                    if(s.getFirstChild()!=null)
                                    {
                                        s.getElementsByTagName("type");
                                        String type = getNodevalue(s.getElementsByTagName("type"));
                                        String start = getNodevalue(s.getElementsByTagName("start"));
                                        String end = getNodevalue(s.getElementsByTagName("end"));
                                        if(!type.equals("") && !start.equals("") && !end.equals(""))
                                        {
                                            int istart = Integer.parseInt(start);
                                            int iend = Integer.parseInt(end);
                                            for(int o=istart;o<iend;o++)
                                            {
                                                choice.addStyle(type, l, o);
                                            }
                                            //Toast t = Toast.makeText(context, type+" "+istart+" "+iend, Toast.LENGTH_SHORT);
                                            //t.show();
                                        }
                                    }
                            }
                        }
                    }
                }
            }
            
            //examlist.add(ex);
            exammap.put((i+1),ex);
        }
        Variable.examinations = exammap;
    }
    private NodeList getSubnode(NodeList node, int i, String childname)
    {
        
            if(node==null)
            {
                return null;
            }
            else
            {
                Node nNode = node.item(i);
                if (node.getLength()>0 && nNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element eElement = (Element) nNode;
                    return eElement.getElementsByTagName(childname);
                }
                else
                {
                    return null;
                }
            }
    }
    private String getAttributevalue(NodeList node, int i, String value)
    {
        
        if(node==null)
        {
            return "";
        }
        else
        {
            Node nodelist = node.item(i);
            if (node.getLength()>0 && nodelist.getNodeType() == Node.ELEMENT_NODE) 
            {
                Element element = (Element) nodelist;     
                return element.getAttribute(value);
            }
            else
            {
                return "";
            }
        }
    }
    private String getNodevalue(NodeList nodelist)
    {
            Element e = (Element)nodelist.item(0);
            Node node = e.getFirstChild();
            if(node==null)
            {
                return "";
            }
            else
            {
                return node.getNodeValue();
            }
    }
    public String getExamtitle()
    {
        return examtitle;
    }
    public String getExamtype()
    {
        return examtype;
    }
    public String getShuffle()
    {
        return shuffle;
    }
    public String getTotal()
    {
        return total;
    }
    public String getCom()
    {
        return com;
    }
    public String getStudentid()
    {
        return studentid;
    }
    public void setStudentid(String studentid)
    {
        this.studentid = studentid;
    }
    public String getStudentname()
    {
        return studentname;
    }
    public void setStudentname(String studentname)
    {
        this.studentname = studentname;
    }
    public String getSet()
    {
        return set;
    }
    public String getRow()
    {
        return row;
    }
    public void setRow(String row)
    {
        this.row = row;
    }
    public ArrayList getSurvey()
    {
        return survey;
    }
    public HashMap<Integer,Examination> getExamlist()
    {
        return exammap;
    }
    public class Examination{
        String column="";
        String examid="";
        String examtype="";
        String correct="";
        String selectedanswer="";
        String topiccode="";
        String examlevel="";
        String medcode="";
        String textcode="";
        String reference="";
        ArrayList question = new ArrayList();
        ArrayList choice = new ArrayList();
        
        public void setColumn(String column)
        {
            this.column = column;
        }
        public String getColumn()
        {
            return column;
        }
        public void setExamid(String examid)
        {
            this.examid = examid;
        }
        public String getExamid()
        {
            return examid;
        }
        public void setExamtype(String examtype)
        {
            this.examtype = examtype;
        }
        public String getExamtype()
        {
            return examtype;
        }
        public void setCorrect(String correct)
        {
            this.correct = correct;
        }
        public String getCorrect()
        {
            return correct;
        }
        public void setSelectedanswer(String selectedanswer)
        {
            this.selectedanswer = selectedanswer;
        }
        public String getSelectedanswer()
        {
            return selectedanswer;
        }
        public void setTopiccode(String topiccode)
        {
            this.topiccode = topiccode;
        }
        public String getTopiccode()
        {
            return topiccode;
        }
        public void setExamlevel(String examlevel)
        {
            this.examlevel = examlevel;
        }
        public String getExamlevel()
        {
            return examlevel;
        }
        public void setMedcode(String medcode)
        {
            this.medcode = medcode;
        }
        public String getMedcode()
        {
            return medcode;
        }
        public void setTextcode(String textcode)
        {
            this.textcode = textcode;
        }
        public String getTextcode()
        {
            return textcode;
        }
        public void setReference(String reference)
        {
            this.reference = reference;
        }
        public String getReference()
        {
            return reference;
        }
        public void addQuestion(Question q)
        {
            question.add(q);
        }
        public ArrayList getQuestion()
        {
            return question;
        }
        public void addChoice(Choice c)
        {
            choice.add(c);
        }
        public ArrayList getChoice()
        {
            return choice;
        }
    }
    public class Question{
        String type;
        String value;
        String width = "";
        String height = "";
        ArrayList style = new ArrayList();
        public Question(String type, String value, String width, String height)
        {
            this.type = type;
            this.value = value;
            this.width = width;
            this.height = height;
            for(int i=0;i<value.length();i++)
            {
                style.add(new ArrayList());
            }
        }
        public String getType()
        {
            return type;
        }
        public String getValue()
        {
            return value;
        }
        public String getWidth()
        {
        	return width;
        }
        public String getHeight()
        {
        	return height;
        }
        public void addStyle(String s,int i)
        {
            ((ArrayList)style.get(i)).add(s);
        }
        public ArrayList getStyle()
        {
            return style;
        }
    }
    public class Choice{
        String id;
        ArrayList stem = new ArrayList();
        ArrayList style = new ArrayList();
        public Choice(String id)
        {
            this.id = id;
        }
        public String getId()
        {
            return id;
        }
        public void addStem(String type, String value)
        {
            String[] v = new String[2];
            v[0] = type;
            v[1] = value;
            stem.add(v);
            ArrayList substyle = new ArrayList();
            for(int i=0;i<value.length();i++)
            {
                substyle.add(new ArrayList());
            }
            style.add(substyle);
        }
        public ArrayList getStem()
        {
            return stem;
        }
        public void addStyle(String s,int i,int j)
        {
            ((ArrayList)((ArrayList)style.get(i)).get(j)).add(s);
        }
        public ArrayList getStyle(int i)
        {
            return (ArrayList) style.get(i);
        }
    }
}

