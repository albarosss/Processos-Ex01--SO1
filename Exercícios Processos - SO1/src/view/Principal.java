package view;

import javax.swing.JOptionPane;
import controller.RedesController;

public class Principal {

	public static void main (String [] args)
	{
		RedesController CallController = new RedesController();
		String SO = System.getProperty("os.name");
		System.out.println(SO);
		int opc = 0;
		while(opc != 9) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("Digite uma opção:\n \n1 - IP \n2 - Ping \n9 - Sair"));
			switch(opc) {
				case 1:	CallController.ip(SO);
					break;
				case 2:	CallController.ping(SO);
					break;
				case 9:	System.exit(0);
				default: JOptionPane.showMessageDialog(null, "Valor inválido");
					break;
			}
		}
	}
}
