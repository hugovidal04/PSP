package UD4_generacion_de_servicios_en_rede.Ejercicios.psp02_servizos_en_rede;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class PSP06_SSH_JschInteractivoLog {
	public static void main(String[] args) {
		String host = "test.rebex.net";
		String usuario = "demo";
		String clave = "password";
		int puerto = 22;
		
		//Generar nombre del fichero con fecha actual
		String fechaActual = new SimpleDateFormat("yyMMdd").format(new Date());
		String nombreLog = "sesion_" + fechaActual + ".log";


		try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(nombreLog, true))) {
			//Guardar fecha y hora de inicio
			Date inicioSesion = new Date();
			String fechaHoraInicio = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(inicioSesion);
			logWriter.write("Inicio de sesión: " + fechaHoraInicio + "\n");
			logWriter.write("Usuario: " + usuario + " - Servidor: " + host + "\n");
			logWriter.write("*********************************************\n");
			logWriter.flush();
			
			
			//Crear sesión SSH
			JSch jsch = new JSch();
			Session sesion = jsch.getSession(usuario, host, puerto);
			sesion.setPassword(clave);
			//Conexión sin verificar laq clave del host (evita advertencia seguridad)
			sesion.setConfig("StrictHostKeyChecking", "no");

			System.out.println("Conectando al servidor SSH...");
			sesion.connect(); //Establecemos conexión
			System.out.println("Conectado correctamente a " + host);

			Scanner scanner = new Scanner(System.in);
			String comando;

			while (true) {
				System.out.print("\nIntroduce comando SSH ('exit' para salir): ");
				comando = scanner.nextLine();

				if ("exit".equalsIgnoreCase(comando)) {
					break; //Salimos del bucle
				}

				//Guardar comando en el log
				String fechaHoraComando = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				
				logWriter.write(fechaHoraComando + " - Comando: " + comando + "\n");
				logWriter.flush();
				
				ejecutarComando(sesion, comando, logWriter);

			}
			
			//Cerrar la conexión
			sesion.disconnect();
			scanner.close();
			System.out.println("Conexión cerrada...");
			
			
			//Guarda fecha, hora y duración de la conexión
			Date finSesion = new Date();
			String fechaHoraFin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(finSesion);
			long duracionSeg = (finSesion.getTime() - inicioSesion.getTime()) / 1000;
			
			logWriter.write("*********************************************\n");
			logWriter.write("Fin de sesión: " + fechaHoraFin + "\n");
			logWriter.write("Duración de la sesión: " + duracionSeg + " segundos\n");
			logWriter.flush();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		public static void ejecutarComando(Session sesion, String comando, BufferedWriter logWriter) {
			try {
				ChannelExec canal = (ChannelExec) sesion.openChannel("exec");
				canal.setCommand(comando);

				//Capturar la salida del comando
				canal.setInputStream(null);
				canal.setErrStream(System.err);
				InputStream input = canal.getInputStream();

				canal.connect();
				System.out.println("Ejecutando: " + comando);

				//Leer y mostrar la salida del comando
				byte[] tmp = new byte[1024];
				StringBuilder salidaComando = new StringBuilder();
				while(true) {
					while(input.available() > 0) {
						int i = input.read(tmp, 0, 1024);
						if (i < 0) break;
						String resultado = new String(tmp, 0, i);
						System.out.print(resultado);
						salidaComando.append(resultado);
					}
					if (canal.isClosed()) {
						break;
					}
					Thread.sleep(500);
				}

				//Guardar la salida del comando en el log
				logWriter.write("Salida del comando:\n" + salidaComando.toString() + "\n");
				logWriter.flush();
				
				
				//Cerrar canal
				canal.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}