import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.DefaultListModel;
import javax.activation.*;

import java.util.Timer;
import java.util.TimerTask;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NMS {
	public static void main(String[] args) throws UnknownHostException, IOException {

		/*
		 * Pinging the Network Devices and Creating the Report.
		 */

		InetAddress inet;
		String report;
		// Create the format of the Date.
		DateFormat datentimeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		// Get current date and time.
		Date datentime = new Date();

		report = datentimeFormat.format(datentime) + System.getProperty("line.separator");
		// System.out.println(report);
		report += "Beginning of the Report" + System.getProperty("line.separator") + "________________________"
				+ System.getProperty("line.separator");

		BufferedReader in = null;
		FileReader fr = null;
		List<String> list = new ArrayList<String>();

		try {
			fr = new FileReader(System.getProperty("user.dir") + "\\Files\\IP Addresses.txt");
			in = new BufferedReader(fr);
			String str;
			while ((str = in.readLine()) != null) {
				list.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
			fr.close();
		}

		for (String d : list) {
			String[] parts = d.split("\\.");
			// System.out.println(parts[0] + parts[1] + parts[2] + parts[3]);
			int part1 = Integer.parseInt(parts[0]);
			int part2 = Integer.parseInt(parts[1]);
			int part3 = Integer.parseInt(parts[2]);
			int part4 = Integer.parseInt(parts[3]);
			for (int x = 0; x < 4; x++) {
				inet = InetAddress.getByAddress(new byte[] { (byte) part1, (byte) part2, (byte) part3, (byte) part4 });
				// System.out.println("Sending Ping Request to " + inet);
				if (inet.isReachable(32)) {
					report += inet.getHostName() + " is reachable" + System.getProperty("line.separator");
					break;
				} else {
					if (x == 3) {
						report += inet.getHostName() + " is NOT reachable" + System.getProperty("line.separator");
					}
				}
			}
		}
		/*
		 * for (int x = 0; x < 4; x++) { inet = InetAddress.getByAddress(new
		 * byte[] { (byte) 172, 20, 20, (byte) 210 }); // System.out.println(
		 * "Sending Ping Request to " + inet); if (inet.isReachable(32)) {
		 * report += inet.getHostName() + " is reachable" +
		 * System.getProperty("line.separator"); break; } else { if (x == 3) {
		 * report += inet.getHostName() + " is NOT reachable" +
		 * System.getProperty("line.separator"); } } }
		 * 
		 * for (int x = 0; x < 4; x++) { inet = InetAddress.getByAddress(new
		 * byte[] { (byte) 172, 20, 20, 1 }); // System.out.println(
		 * "Sending Ping Request to " + inet); if (inet.isReachable(32)) {
		 * report += inet.getHostName() + " is reachable" +
		 * System.getProperty("line.separator"); break; } else { if (x == 3) {
		 * report += inet.getHostName() + " is NOT reachable" +
		 * System.getProperty("line.separator"); } } }
		 * 
		 * for (byte i = 3; i <= 9; i++) { for (int x = 0; x < 4; x++) { inet =
		 * InetAddress.getByAddress(new byte[] { (byte) 172, 20, 20, i }); //
		 * System.out.println("Sending Ping Request to " + inet); if
		 * (inet.isReachable(32)) { report += inet.getHostName() +
		 * " is reachable" + System.getProperty("line.separator"); break; } else
		 * { if (x == 3) { report += inet.getHostName() + " is NOT reachable" +
		 * System.getProperty("line.separator"); } } } }
		 * 
		 * for (byte i = 23; i <= 40; i++) { if (i == 35) { for (int x = 0; x <
		 * 8; x++) { inet = InetAddress.getByAddress(new byte[] { (byte) 172,
		 * 20, 20, i }); // System.out.println("Sending Ping Request to " +
		 * inet); if (inet.isReachable(32)) { report += inet.getHostName() +
		 * " is reachable" + System.getProperty("line.separator"); break; } else
		 * { if (x == 7) { report += inet.getHostName() + " is NOT reachable" +
		 * System.getProperty("line.separator"); } } } } else { for (int x = 0;
		 * x < 4; x++) { inet = InetAddress.getByAddress(new byte[] { (byte)
		 * 172, 20, 20, i }); // System.out.println("Sending Ping Request to " +
		 * inet); if (inet.isReachable(32)) { report += inet.getHostName() +
		 * " is reachable" + System.getProperty("line.separator"); break; } else
		 * { if (x == 3) { report += inet.getHostName() + " is NOT reachable" +
		 * System.getProperty("line.separator"); } } } } }
		 * 
		 * for (int x = 0; x < 4; x++) { inet = InetAddress.getByAddress(new
		 * byte[] { (byte) 172, 20, 20, 45 }); // System.out.println(
		 * "Sending Ping Request to " + inet); if (inet.isReachable(32)) {
		 * report += inet.getHostName() + " is reachable" +
		 * System.getProperty("line.separator"); break; } else { if (x == 3) {
		 * report += inet.getHostName() + " is NOT reachable" +
		 * System.getProperty("line.separator"); } } } for (int x = 0; x < 4;
		 * x++) { inet = InetAddress.getByAddress(new byte[] { (byte) 172, 20,
		 * 20, (byte) 225 }); // System.out.println("Sending Ping Request to " +
		 * inet); if (inet.isReachable(32)) { report += inet.getHostName() +
		 * " is reachable" + System.getProperty("line.separator"); break; } else
		 * { if (x == 3) { report += inet.getHostName() + " is NOT reachable" +
		 * System.getProperty("line.separator"); } } }
		 * 
		 * for (byte i = (byte) 230; i <= (byte) 234; i++) { for (int x = 0; x <
		 * 4; x++) { inet = InetAddress.getByAddress(new byte[] { (byte) 172,
		 * 20, 20, i }); // System.out.println("Sending Ping Request to " +
		 * inet); if (inet.isReachable(32)) { report += inet.getHostName() +
		 * " is reachable" + System.getProperty("line.separator"); break; } else
		 * { if (x == 3) { report += inet.getHostName() + " is NOT reachable" +
		 * System.getProperty("line.separator"); } } } }
		 */

		report += "________________________" + System.getProperty("line.separator");
		report += "End of Report";
		// System.out.println(report);

		/*
		 * Create and Send the Email.
		 */
		BufferedReader in2 = null;
		FileReader fr2 = null;
		List<String> lista2 = new ArrayList<String>();
		try {
			fr2 = new FileReader(System.getProperty("user.dir") + "\\Files\\Emails.txt");
			in2 = new BufferedReader(fr2);
			String str2;
			while ((str2 = in2.readLine()) != null) {
				lista2.add(str2);
			}
			java.util.Collections.sort(lista2);
			for (String d2 : lista2) {

				// Recipient's email ID needs to be mentioned.
				String to = d2;

				// Sender's email ID needs to be mentioned
				String from = "fmiserverupdate@gmail.com";

				// Assuming you are sending email from localhost
				String host = "smtp.gmail.com";

				String password = "!jp3921fm1";

				// Get system properties
				Properties properties = System.getProperties();

				// Setup mail server
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.host", host);
				properties.put("mail.smtp.password", password);
				properties.put("mail.smtp.port", 587);
				properties.put("mail.smtp.auth", "true");

				// Get the default Session object.
				Session session = Session.getDefaultInstance(properties, null);

				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				try {

					// Set From: header field of the header.
					message.setFrom(new InternetAddress(from));

					// Set To: header field of the header.
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

					// Set Subject: header field
					message.setSubject("Network Status Update");

					// Now set the actual message
					message.setText(report);

					/*
					 * PrintWriter writer = new
					 * PrintWriter(dateFormat.format(date) +
					 * " - Server Report.txt", "UTF-8"); writer.println(report);
					 * writer.close();
					 */
					// Send message
					// Transport.send(message);
					Transport tran = session.getTransport("smtp");
					tran.connect(host, from, password);
					tran.sendMessage(message, message.getAllRecipients());
					//System.out.println("Sent message successfully....");
				} catch (MessagingException mex) {
					mex.printStackTrace();
				}
			}
		} catch (IOException e) {
			// exception handling left as an exercise for
			// the
			// reader
		}

		/*
		 * Creating the Directory to store the log files.
		 */

		DateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");
		// get current date time with Date()
		Date date = new Date();

		File path = new File(System.getProperty("user.dir") + "\\Files\\Network Status Log Files\\" + dateFormat.format(date));

		// if the directory does not exist, create it
		if (!path.exists()) {
			// System.out.println("creating directory: " + path);
			boolean result = false;

			try {
				path.mkdirs();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				//System.out.println("Directory " + date + " created");
			}
		}

		DateFormat timeFormat = new SimpleDateFormat("HH.mm");
		// get current date time with Date()
		Date time = new Date();

		// Path file = Paths.get(path + " " + time + " Network Status.txt");
		// Files.write(file, report, Charset.forName("UTF-8"));

		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(path + "\\" + timeFormat.format(time) + " Network Status.txt"), "utf-8"))) {
			writer.write(report);
		}
	}
}
