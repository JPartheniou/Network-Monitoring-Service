import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import java.awt.GridLayout;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import junit.framework.Test;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import javax.swing.SwingConstants;

public class NMSGui extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField previouslyFocusedTextBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NMSGui frame = new NMSGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public NMSGui() throws IOException {
		setTitle("Network Monitoring Software");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 535);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		BufferedReader in = null;
		FileReader fr = null;
		List<String> lista = new ArrayList<String>();

		try {
			fr = new FileReader(System.getProperty("user.dir") + "\\Files\\IP Addresses.txt");
			in = new BufferedReader(fr);
			String str;
			while ((str = in.readLine()) != null) {
				lista.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
			fr.close();
		}

		DefaultListModel model = new DefaultListModel();
		Collections.sort(lista, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return extractInt(o1) - extractInt(o2);
			}

			int extractInt(String s) {
				String num = s.replaceAll("\\D", "");
				// return 0 if no digits found
				return num.isEmpty() ? 0 : Integer.parseInt(num);
			}
		});
		for (String d : lista) {
			model.addElement(d);
		}

		BufferedReader in_1 = null;
		FileReader fr_1 = null;
		List<String> lista_1 = new ArrayList<String>();

		try {
			fr_1 = new FileReader(System.getProperty("user.dir") + "\\Files\\Emails.txt");
			in_1 = new BufferedReader(fr_1);
			String str;
			while ((str = in_1.readLine()) != null) {
				lista_1.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in_1.close();
			fr_1.close();
		}

		DefaultListModel model_1 = new DefaultListModel();
		java.util.Collections.sort(lista_1);
		/*
		 * Collections.sort(lista_1, new Comparator<String>() { public int
		 * compare(String o1, String o2) { return extractInt(o1) -
		 * extractInt(o2); }
		 * 
		 * int extractInt(String s) { String num = s.replaceAll("\\D", ""); //
		 * return 0 if no digits found return num.isEmpty() ? 0 :
		 * Integer.parseInt(num); } });
		 */
		for (String d : lista_1) {
			model_1.addElement(d);
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(369, 37, 231, 192);
		contentPane.add(scrollPane);
		JList list = new JList(model);
		list.setFont(new Font("Verdana", Font.PLAIN, 14));
		scrollPane.setViewportView(list);

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList list2 = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 1) {
					int index = list2.locationToIndex(mouseEvent.getPoint());
					if (index >= 0) {
						Object o = list2.getModel().getElementAt(index);

						String string = o.toString().replace("[", "").replace("]", "");

						String[] parts = string.split("\\.");
						// System.out.println(/* parts[0] + parts[1] + parts[2]
						// + parts[3] */string);
						String part1 = parts[0];
						String part2 = parts[1];
						String part3 = parts[2];
						String part4 = parts[3];
						textField.setText(part1);
						textField_1.setText(part2);
						textField_2.setText(part3);
						textField_3.setText(part4);
					}
				}

			}
		};
		list.addMouseListener(mouseListener);

		JList list_1 = new JList(model_1);
		list_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(369, 289, 231, 90);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(list_1);

		MouseListener mouseListener2 = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent2) {
				JList list2 = (JList) mouseEvent2.getSource();
				if (mouseEvent2.getClickCount() == 1) {
					int index = list2.locationToIndex(mouseEvent2.getPoint());
					if (index >= 0) {
						Object o = list2.getModel().getElementAt(index);
						String string = o.toString();
						// System.out.println(/* parts[0] + parts[1] + parts[2]
						// + parts[3] */string);
						textField_4.setText(string);
					}
				}

			}
		};
		list_1.addMouseListener(mouseListener2);

		try {
			textField = new JTextField();
			textField.setToolTipText("Enter a valid section of the IP Address (values between 0 - 255).");
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			// textField.setToolTipText("");
			textField.setFont(new Font("Verdana", Font.PLAIN, 14));
			textField.setText("172");
			textField.setBounds(20, 37, 66, 37);
			contentPane.add(textField);
			textField.setColumns(10);
			textField.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					previouslyFocusedTextBox = textField;
					char c = e.getKeyChar();
					// int val = textField.getValue();
					if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
						e.consume(); // ignore event
					}
					list.clearSelection();
				}
			});
			textField.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					previouslyFocusedTextBox = textField;
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {
					previouslyFocusedTextBox = textField;

				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					previouslyFocusedTextBox = textField;
				}
			});
		} catch (Exception e) {

		}

		try {
			textField_1 = new JTextField();
			textField_1.setToolTipText("Enter a valid section of the IP Address (values between 0 - 255).");
			textField_1.setHorizontalAlignment(SwingConstants.CENTER);
			// textField_1.setToolTipText("");
			textField_1.setFont(new Font("Verdana", Font.PLAIN, 14));
			textField_1.setText("20");
			textField_1.setBounds(96, 37, 66, 37);
			contentPane.add(textField_1);
			textField_1.setColumns(10);
			textField_1.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					previouslyFocusedTextBox = textField_1;
					char c = e.getKeyChar();
					// int val = textField.getValue();
					if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
						e.consume(); // ignore event
					}
					list.clearSelection();
				}
			});
			textField_1.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					previouslyFocusedTextBox = textField_1;
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {
					previouslyFocusedTextBox = textField_1;

				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					previouslyFocusedTextBox = textField_1;
				}
			});
		} catch (Exception e) {

		}

		try {
			textField_2 = new JTextField();
			textField_2.setToolTipText("Enter a valid section of the IP Address (values between 0 - 255).");
			textField_2.setHorizontalAlignment(SwingConstants.CENTER);
			// textField_2.setToolTipText("");
			textField_2.setFont(new Font("Verdana", Font.PLAIN, 14));
			textField_2.setText("20");
			textField_2.setBounds(172, 37, 66, 37);
			contentPane.add(textField_2);
			textField_2.setColumns(10);
			textField_2.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					previouslyFocusedTextBox = textField_2;
					char c = e.getKeyChar();
					// int val = textField.getValue();
					if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
						e.consume(); // ignore event
					}
					list.clearSelection();
				}
			});
			textField_2.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					previouslyFocusedTextBox = textField_2;
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {
					previouslyFocusedTextBox = textField_2;

				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					previouslyFocusedTextBox = textField_2;
				}
			});
		} catch (Exception e) {

		}

		try {
			textField_3 = new JTextField();
			textField_3.setToolTipText("Enter a valid section of the IP Address (values between 0 - 255).");
			textField_3.setHorizontalAlignment(SwingConstants.CENTER);
			// textField_3.setToolTipText("");
			textField_3.setFont(new Font("Verdana", Font.PLAIN, 14));
			textField_3.setBounds(248, 37, 66, 37);
			contentPane.add(textField_3);
			textField_3.setColumns(10);
			textField_3.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					previouslyFocusedTextBox = textField_3;
					char c = e.getKeyChar();
					// int val = textField.getValue();
					if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
						e.consume(); // ignore event
					}
					list.clearSelection();
				}
			});
			textField_3.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					previouslyFocusedTextBox = textField_3;
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {
					previouslyFocusedTextBox = textField_3;

				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					previouslyFocusedTextBox = textField_3;
				}
			});
		} catch (Exception e) {

		}
		try {
			textField_4 = new JTextField();
			textField_4.setToolTipText("Enter a valid Email Address (e.g. me@gmail.com).");
			textField_4.setHorizontalAlignment(SwingConstants.CENTER);
			// textField_3.setToolTipText("");
			textField_4.setFont(new Font("Verdana", Font.PLAIN, 14));
			textField_4.setBounds(20, 292, 231, 37);
			contentPane.add(textField_4);
			textField_4.setColumns(10);
			textField_4.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					previouslyFocusedTextBox = textField_4;
					// int val = textField.getValue();

					list_1.clearSelection();
				}
			});
			textField_4.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					previouslyFocusedTextBox = textField_4;
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {
					previouslyFocusedTextBox = textField_4;

				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					previouslyFocusedTextBox = textField_4;
				}
			});
		} catch (Exception e) {

		}

		JLabel lblIpAddress = new JLabel("IP Address");
		lblIpAddress.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblIpAddress.setBounds(20, 11, 110, 28);
		contentPane.add(lblIpAddress);

		JLabel lblIpAddresses = new JLabel("IP Addresses");
		lblIpAddresses.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblIpAddresses.setBounds(369, 11, 116, 28);
		contentPane.add(lblIpAddresses);

		JButton btnAdd = new JButton("Add");
		btnAdd.setToolTipText("Add the IP/Email Address.");
		btnAdd.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (previouslyFocusedTextBox.equals(textField) || previouslyFocusedTextBox.equals(textField_1)
						|| previouslyFocusedTextBox.equals(textField_2)
						|| previouslyFocusedTextBox.equals(textField_3)) {
					BufferedReader in3 = null;
					FileReader fr3 = null;
					List<String> lista3 = new ArrayList<String>();
					try {
						fr3 = new FileReader(System.getProperty("user.dir") + "\\Files\\IP Addresses.txt");
						in3 = new BufferedReader(fr3);
						String str3;
						while ((str3 = in3.readLine()) != null) {
							lista3.add(str3);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							in3.close();
							fr3.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					int t1 = 256;
					int t2 = 256;
					int t3 = 256;
					int t4 = 256;

					if ((textField.getDocument().getLength() > 3 && textField.getDocument().getLength() < 1)
							|| (textField_1.getDocument().getLength() > 3 && textField_1.getDocument().getLength() < 1)
							|| (textField_2.getDocument().getLength() > 3 && textField_2.getDocument().getLength() < 1)
							|| (textField_3.getDocument().getLength() > 3
									&& textField_3.getDocument().getLength() < 1)) {
						JOptionPane.showMessageDialog(null,
								"One or more values in the text fields are not valid. The values should be between 0 and 255.",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						t1 = Integer.parseInt(textField.getText());
						t2 = Integer.parseInt(textField_1.getText());
						t3 = Integer.parseInt(textField_2.getText());
						t4 = Integer.parseInt(textField_3.getText());
					}

					if (t1 > 255 || t2 > 255 || t3 > 255 || t4 > 255) {
						JOptionPane.showMessageDialog(null,
								"One or more values in the text fields are not valid. The values should be between 0 and 255.",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						String address = textField.getText() + "." + textField_1.getText() + "." + textField_2.getText()
								+ "." + textField_3.getText();
						boolean exists = false;
						for (String z : lista3) {
							if (z.contains(address)) {
								exists = true;
								break;
							}
						}

						if (exists == true) {
							JOptionPane.showMessageDialog(null, "This IP Address " + address + " already exists.",
									"Warning", JOptionPane.WARNING_MESSAGE);
						} else {
							lista3.add(address);
							Collections.sort(lista3, new Comparator<String>() {
								public int compare(String o1, String o2) {
									return extractInt(o1) - extractInt(o2);
								}

								int extractInt(String s) {
									String num = s.replaceAll("\\D", "");
									// return 0 if no digits found
									return num.isEmpty() ? 0 : Integer.parseInt(num);
								}
							});
							FileWriter writer;
							try {
								writer = new FileWriter(System.getProperty("user.dir") + "\\Files\\IP Addresses.txt");
								for (String str : lista3) {
									str = str.trim();
									writer.write(str + System.getProperty("line.separator"));
								}
								JOptionPane.showMessageDialog(null,
										"The IP Address " + address + " was added to the list successfully.", "Success",
										JOptionPane.INFORMATION_MESSAGE);
								writer.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							BufferedReader in2 = null;
							FileReader fr2 = null;
							List<String> lista2 = new ArrayList<String>();
							try {
								fr2 = new FileReader(System.getProperty("user.dir") + "\\Files\\IP Addresses.txt");
								in2 = new BufferedReader(fr2);
								String str2;
								while ((str2 = in2.readLine()) != null) {
									lista2.add(str2);
								}
								DefaultListModel model2 = new DefaultListModel();
								// java.util.Collections.sort(lista2);
								Collections.sort(lista2, new Comparator<String>() {
									public int compare(String o1, String o2) {
										return extractInt(o1) - extractInt(o2);
									}

									int extractInt(String s) {
										String num = s.replaceAll("\\D", "");
										// return 0 if no digits found
										return num.isEmpty() ? 0 : Integer.parseInt(num);
									}
								});
								for (String d2 : lista2) {
									model2.addElement(d2);
								}
								list.setModel(model2);
							} catch (IOException e) {
								// exception handling left as an exercise for
								// the
								// reader
							}
						}
					}
				} else if (previouslyFocusedTextBox.equals(textField_4)) {
					BufferedReader in3 = null;
					FileReader fr3 = null;
					List<String> lista3 = new ArrayList<String>();
					try {
						fr3 = new FileReader(System.getProperty("user.dir") + "\\Files\\Emails.txt");
						in3 = new BufferedReader(fr3);
						String str3;
						while ((str3 = in3.readLine()) != null) {
							lista3.add(str3);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							in3.close();
							fr3.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					int atposition = 0, dotposition = 0, flag = 0, count = 0;
					Emailvalidator emailValidator = new Emailvalidator();
					if (!emailValidator.validate(textField_4.getText().trim())) {
						JOptionPane.showMessageDialog(null, "Invalid Email Address.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						boolean exists = false;
						for (String z : lista3) {
							if (z.contains(textField_4.getText().trim())) {
								exists = true;
								break;
							}
						}
						if (exists) {
							JOptionPane.showMessageDialog(null,
									"This Email Address " + textField_4.getText().trim() + " already exists.",
									"Warning", JOptionPane.WARNING_MESSAGE);
						} else {
							lista3.add(textField_4.getText().trim());
							java.util.Collections.sort(lista3);
							/*
							 * Collections.sort(lista3, new Comparator<String>()
							 * { public int compare(String o1, String o2) {
							 * return extractInt(o1) - extractInt(o2); }
							 * 
							 * int extractInt(String s) { String num =
							 * s.replaceAll("\\D", ""); // return 0 if no digits
							 * found return num.isEmpty() ? 0 :
							 * Integer.parseInt(num); } });
							 */
							FileWriter writer;
							try {
								writer = new FileWriter(System.getProperty("user.dir") + "\\Files\\Emails.txt");
								for (String str : lista3) {
									str = str.trim();
									writer.write(str + System.getProperty("line.separator"));
								}
								JOptionPane.showMessageDialog(null,
										"The Email Address " + textField_4.getText().trim()
												+ " was added to the list successfully.",
										"Success", JOptionPane.INFORMATION_MESSAGE);
								writer.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
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
							DefaultListModel model2 = new DefaultListModel();
							// java.util.Collections.sort(lista2);
							java.util.Collections.sort(lista2);
							/*
							 * Collections.sort(lista2, new Comparator<String>()
							 * { public int compare(String o1, String o2) {
							 * return extractInt(o1) - extractInt(o2); }
							 * 
							 * int extractInt(String s) { String num =
							 * s.replaceAll("\\D", ""); // return 0 if no digits
							 * found return num.isEmpty() ? 0 :
							 * Integer.parseInt(num); } });
							 */
							for (String d2 : lista2) {
								model2.addElement(d2);
							}
							list_1.setModel(model2);
						} catch (IOException e) {
							// exception handling left as an exercise for
							// the
							// reader
						}
					}
				}
			}
		});

		btnAdd.setBounds(20, 435, 89, 44);
		contentPane.add(btnAdd);

		JButton btnSearch = new JButton("Search");
		btnSearch.setToolTipText("Search for the IP/Email Address.");
		btnSearch.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (previouslyFocusedTextBox.equals(textField) || previouslyFocusedTextBox.equals(textField_1)
						|| previouslyFocusedTextBox.equals(textField_2)
						|| previouslyFocusedTextBox.equals(textField_3)) {
					BufferedReader in3 = null;
					FileReader fr3 = null;
					List<String> lista3 = new ArrayList<String>();
					try {
						fr3 = new FileReader(System.getProperty("user.dir") + "\\Files\\IP Addresses.txt");
						in3 = new BufferedReader(fr3);
						String str3;
						while ((str3 = in3.readLine()) != null) {
							lista3.add(str3);
						}
					} catch (Exception g) {
						g.printStackTrace();
					} finally {
						try {
							in3.close();
							fr3.close();
						} catch (IOException g) {
							// TODO Auto-generated catch block
							g.printStackTrace();
						}
					}

					int t1 = 256;
					int t2 = 256;
					int t3 = 256;
					int t4 = 256;

					if ((textField.getDocument().getLength() > 3 && textField.getDocument().getLength() < 1)
							|| (textField_1.getDocument().getLength() > 3 && textField_1.getDocument().getLength() < 1)
							|| (textField_2.getDocument().getLength() > 3 && textField_2.getDocument().getLength() < 1)
							|| (textField_3.getDocument().getLength() > 3
									&& textField_3.getDocument().getLength() < 1)) {
						JOptionPane.showMessageDialog(null,
								"One or more values in the text fields are not valid. The values should be between 0 and 255.",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						t1 = Integer.parseInt(textField.getText());
						t2 = Integer.parseInt(textField_1.getText());
						t3 = Integer.parseInt(textField_2.getText());
						t4 = Integer.parseInt(textField_3.getText());
					}

					if (t1 > 255 || t2 > 255 || t3 > 255 || t4 > 255) {
						JOptionPane.showMessageDialog(null,
								"One or more values in the text fields are not valid. The values should be between 0 and 255.",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						String address = textField.getText() + "." + textField_1.getText() + "." + textField_2.getText()
								+ "." + textField_3.getText();
						boolean exists = false;
						int ind = -1;
						for (String z : lista3) {
							ind++;
							if (z.contains(address)) {
								exists = true;
								break;
							}
						}
						if (exists) {
							list.setSelectedIndex(ind);
							list.ensureIndexIsVisible(list.getSelectedIndex());
						} else {
							JOptionPane.showMessageDialog(null, "The IP Address " + address + " does not exist.",
									"Warning", JOptionPane.WARNING_MESSAGE);
						}
					}
				} else if (previouslyFocusedTextBox.equals(textField_4)) {
					BufferedReader in3 = null;
					FileReader fr3 = null;
					List<String> lista3 = new ArrayList<String>();
					try {
						fr3 = new FileReader(System.getProperty("user.dir") + "\\Files\\Emails.txt");
						in3 = new BufferedReader(fr3);
						String str3;
						while ((str3 = in3.readLine()) != null) {
							lista3.add(str3);
						}
					} catch (Exception g) {
						g.printStackTrace();
					} finally {
						try {
							in3.close();
							fr3.close();
						} catch (IOException g) {
							// TODO Auto-generated catch block
							g.printStackTrace();
						}
					}
					String emad = textField_4.getText();
					boolean exists = false;
					int ind = -1;
					for (String z : lista3) {
						ind++;
						if (z.contains(emad)) {
							exists = true;
							break;
						}
					}
					if (exists) {
						list_1.setSelectedIndex(ind);
						list_1.ensureIndexIsVisible(list_1.getSelectedIndex());
					} else {
						JOptionPane.showMessageDialog(null,
								"The Email Address " + textField_4.getText().trim() + " does not exist.", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}

				}
			}
		});
		btnSearch.setBounds(119, 435, 89, 44);
		contentPane.add(btnSearch);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Delete the IP/Email Address");
		btnDelete.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (previouslyFocusedTextBox.equals(textField) || previouslyFocusedTextBox.equals(textField_1)
						|| previouslyFocusedTextBox.equals(textField_2)
						|| previouslyFocusedTextBox.equals(textField_3)) {

					BufferedReader in3 = null;
					FileReader fr3 = null;
					List<String> lista3 = new ArrayList<String>();
					try {
						fr3 = new FileReader(System.getProperty("user.dir") + "\\Files\\IP Addresses.txt");
						in3 = new BufferedReader(fr3);
						String str3;
						while ((str3 = in3.readLine()) != null) {
							lista3.add(str3);
						}
					} catch (Exception q) {
						q.printStackTrace();
					} finally {
						try {
							in3.close();
							fr3.close();
						} catch (IOException q) {
							// TODO Auto-generated catch block
							q.printStackTrace();
						}
					}

					String address = textField.getText() + "." + textField_1.getText() + "." + textField_2.getText()
							+ "." + textField_3.getText();
					int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete " + address + "?",
							"Delete " + address + "?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {

						DefaultListModel model = (DefaultListModel) list.getModel();
						int selectedIndex = list.getSelectedIndex();
						if (selectedIndex != -1) {
							model.remove(selectedIndex);
						}

						lista3.remove(address);

						Collections.sort(lista3, new Comparator<String>() {
							public int compare(String o1, String o2) {
								return extractInt(o1) - extractInt(o2);
							}

							int extractInt(String s) {
								String num = s.replaceAll("\\D", "");
								// return 0 if no digits found
								return num.isEmpty() ? 0 : Integer.parseInt(num);
							}
						});

						FileWriter writer;
						try {
							writer = new FileWriter(System.getProperty("user.dir") + "\\Files\\IP Addresses.txt");
							for (String str : lista3) {
								str = str.trim();
								if (!str.equals("")) // don't write out blank
														// lines
								{
									writer.write(str + System.getProperty("line.separator"));
								}
							}
							writer.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						BufferedReader in2 = null;
						FileReader fr2 = null;
						List<String> lista2 = new ArrayList<String>();
						try {
							fr2 = new FileReader(System.getProperty("user.dir") + "\\Files\\IP Addresses.txt");
							in2 = new BufferedReader(fr2);
							String str2;
							while ((str2 = in2.readLine()) != null) {
								lista2.add(str2);
							}
						} catch (Exception f) {
							f.printStackTrace();
						} finally {
							try {
								in2.close();
								fr2.close();
							} catch (IOException f) {
								// TODO Auto-generated catch block
								f.printStackTrace();
							}
						}
						DefaultListModel model2 = new DefaultListModel();
						Collections.sort(lista2, new Comparator<String>() {
							public int compare(String o1, String o2) {
								return extractInt(o1) - extractInt(o2);
							}

							int extractInt(String s) {
								String num = s.replaceAll("\\D", "");
								// return 0 if no digits found
								return num.isEmpty() ? 0 : Integer.parseInt(num);
							}
						});
						for (String d2 : lista2) {
							model2.addElement(d2);
						}
						list.setModel(model2);
						JOptionPane.showMessageDialog(null,
								"The IP Address " + address + " was deleted from the list successfully.", "Success",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else if (previouslyFocusedTextBox.equals(textField_4)) {
					BufferedReader in3 = null;
					FileReader fr3 = null;
					List<String> lista3 = new ArrayList<String>();
					try {
						fr3 = new FileReader(System.getProperty("user.dir") + "\\Files\\Emails.txt");
						in3 = new BufferedReader(fr3);
						String str3;
						while ((str3 = in3.readLine()) != null) {
							lista3.add(str3);
						}
					} catch (Exception q) {
						q.printStackTrace();
					} finally {
						try {
							in3.close();
							fr3.close();
						} catch (IOException q) {
							// TODO Auto-generated catch block
							q.printStackTrace();
						}
					}
					int result = JOptionPane.showConfirmDialog(null,
							"Are you sure you wish to delete " + textField_4.getText() + "?",
							"Delete " + textField_4.getText() + "?", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						DefaultListModel model_1 = (DefaultListModel) list_1.getModel();
						int selectedIndex = list_1.getSelectedIndex();
						if (selectedIndex != -1) {
							model_1.remove(selectedIndex);
						}
						String emad = textField_4.getText();

						lista3.remove(emad);
						java.util.Collections.sort(lista3);

						/*
						 * Collections.sort(lista3, new Comparator<String>() {
						 * public int compare(String o1, String o2) { return
						 * extractInt(o1) - extractInt(o2); }
						 * 
						 * int extractInt(String s) { String num =
						 * s.replaceAll("\\D", ""); // return 0 if no digits
						 * found return num.isEmpty() ? 0 :
						 * Integer.parseInt(num); } });
						 */

						FileWriter writer;
						try {
							writer = new FileWriter(System.getProperty("user.dir") + "\\Files\\Emails.txt");
							for (String str : lista3) {
								str = str.trim();
								if (!str.equals("")) // don't write out blank
														// lines
								{
									writer.write(str + System.getProperty("line.separator"));
								}
							}
							writer.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

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
						} catch (Exception f) {
							f.printStackTrace();
						} finally {
							try {
								in2.close();
								fr2.close();
							} catch (IOException f) {
								// TODO Auto-generated catch block
								f.printStackTrace();
							}
						}
						DefaultListModel model2 = new DefaultListModel();
						java.util.Collections.sort(lista2);
						/*
						 * Collections.sort(lista2, new Comparator<String>() {
						 * public int compare(String o1, String o2) { return
						 * extractInt(o1) - extractInt(o2); }
						 * 
						 * int extractInt(String s) { String num =
						 * s.replaceAll("\\D", ""); // return 0 if no digits
						 * found return num.isEmpty() ? 0 :
						 * Integer.parseInt(num); } });
						 */
						for (String d2 : lista2) {
							model2.addElement(d2);
						}
						list_1.setModel(model2);
						JOptionPane.showMessageDialog(null,
								"The Email Address " + textField_4.getText()
										+ " was deleted from the list successfully.",
								"Success", JOptionPane.INFORMATION_MESSAGE);
						textField_4.setText("");
					}
				}
			}
		});
		btnDelete.setBounds(218, 435, 89, 44);
		contentPane.add(btnDelete);

		JButton btnRunPing = new JButton("Run NMS");
		btnRunPing.setToolTipText("Run NMS to see the Status of the Network Devices.");
		btnRunPing.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnRunPing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "\\NMS.jar"));
				} catch (IOException ex) {
					// System.out.println(ex.getMessage());
				}
			}
		});
		btnRunPing.setBounds(490, 435, 110, 44);
		contentPane.add(btnRunPing);

		JLabel lblEmailAddresses = new JLabel("Email Addresses");
		lblEmailAddresses.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblEmailAddresses.setBounds(369, 261, 116, 28);
		contentPane.add(lblEmailAddresses);

		JLabel lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblEmailAddress.setBounds(20, 261, 110, 28);
		contentPane.add(lblEmailAddress);

		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { textField, textField_1,
				textField_2, textField_3, list, btnAdd, btnSearch, btnDelete, lblIpAddresses, lblIpAddress }));
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { textField, textField_1, textField_2,
				textField_3, list, btnAdd, btnSearch, btnDelete, btnRunPing, contentPane }));
	}
}