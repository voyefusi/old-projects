package newsManager;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import searchTree.SearchTreeMap;

public class NewsManagerGUI extends JPanel  {

	static JFrame newsMan = new JFrame("News Manager");
	SearchTreeMap<String, String>  data = new SearchTreeMap<String, String>() ;
	Map<String, SearchTreeMap<Time, String>> newsSource =  new TreeMap<String, SearchTreeMap<Time, String>>();

	private static final long serialVersionUID = 1L;
	private JLabel news;
	private JTextArea display;

		public Time time(String str){
		str = str.trim();
		int colon = str.indexOf(58);	

		String hours = str.substring(0, colon);
		int hour = new Integer(hours);

		String min = str.substring(colon+1, colon+3);
		int mins = new Integer(min);

		String am_pm = str.substring(colon+3, colon+5);
		Time start = new Time(hour, mins, am_pm );
		return start;
	}

	public NewsManagerGUI(){
		display = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(display);
		scrollPane.setPreferredSize(new Dimension(500 , 200));
		add(scrollPane);

		JButton addNewsSource = new JButton("Add News Source");
		add(addNewsSource);

		addNewsSource.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent arg0){	
				String newsName =	JOptionPane.showInputDialog("Enter News Source Name.");
				String file = JOptionPane.showInputDialog( "Enter News Source File:");
				file = "News/" + file;

				data.put(newsName, file);

				try {	
					SearchTreeMap<Time, String> headlines = new SearchTreeMap<Time, String>();
					BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
					String line;

					while ((line = bufferedReader.readLine()) != null) { // null marks the end of file
						int colon = line.indexOf(58);
						String time = line.substring(0, colon+5); // time stores the time key
						Time key = new Time(time);
						line = line.substring(colon+6); // line stores the news headline
						headlines.put(key, line);
					}
					newsSource.put(newsName, headlines);
					bufferedReader.close();

				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		});
		JButton getNews = new JButton("Get News");
		add(getNews);

		getNews.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent arg0){

				String news = JOptionPane.showInputDialog("Enter News Sources Name(s)(comma seperated)");
				String start = JOptionPane.showInputDialog("Enter start time (hour:minute am/pm)");

				Time timeOfStart = new Time(start);
				String endString = JOptionPane.showInputDialog("Enter end time (hour:minute am/pm)");
				Time timeOfEnd = new Time(endString);

				String[] files = news.split(", ");

				SearchTreeMap<Time, String> allTheNews = new SearchTreeMap<Time, String>();
				String output = "";

				for(String newsname: files  ){					
					for(Time t: newsSource.get(newsname).keyList()){
						String newsSourceLine = newsSource.get(newsname).get(t);
						output = allTheNews.get(t);
						if(output == null){
							output= newsSourceLine;
						}
						if(newsSourceLine != null&& (newsSourceLine.equals(output) == false )){
							output = output + ",   " + newsSourceLine;
						}
						allTheNews.put(t, output);
						output = "";
					} 
				}
				allTheNews = allTheNews.subMap(timeOfStart, timeOfEnd);
				String newsOutput = "";
				for(Time t: allTheNews.keyList()){
					newsOutput = newsOutput + t.toString();
					newsOutput = newsOutput + ("   [" + allTheNews.get(t) + " ]");
					newsOutput = newsOutput + "\n";
				}
				display.setText(newsOutput);
			}
		});
		JButton getNewsSource = new JButton("Get News Source");
		add(getNewsSource);
		getNewsSource.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent arg0){
				String newsSources = "";
				for(String s: data.keySet()){
					newsSources = newsSources + s.toString() + "\n";
				}
				display.setText(newsSources);
			}
		});	
		JButton updateNews = new JButton("Update News");
		add(updateNews);

		updateNews.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent arg0){
				String sourceName = JOptionPane.showInputDialog("Enter News Source Name");
				try {			
					SearchTreeMap<Time, String> headlines = new SearchTreeMap<Time, String>();
					BufferedReader bufferedReader = new BufferedReader(new FileReader(data.get(sourceName)));
					String line;
					while ((line = bufferedReader.readLine()) != null) { // null marks the end of file
						int colon = line.indexOf(58);
						String time = line.substring(0, colon+5); // time stores the time
						Time indicator = new Time(time);
						line = line.substring(colon+6); // line stores the news headline
						headlines.put(indicator, line);
					}
					newsSource.put(sourceName, headlines);
					bufferedReader.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				display.setText("Source " + sourceName + " updated.");
			}
		});
	}
	
	private static void createAndShowGUI() {
		newsMan.setContentPane(new NewsManagerGUI());
		newsMan.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		newsMan.pack();
		newsMan.setVisible(true);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}