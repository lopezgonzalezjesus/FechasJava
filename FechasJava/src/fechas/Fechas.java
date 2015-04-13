package fechas;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * Clase que describe el manejo de fechas en Java
 * @author Jesús López González
 *
 */
public class Fechas {

	public static void main(String[] args) {
		
		Date fechaActual = new Date();
		//Fecha completa
		System.out.println("FECHA ACTUAL");
		System.out.println(fechaActual);
		/*
		 * En las primeras versiones de Java, para extraer información de la fecha
		 * disponíamos de metodos que nos facilitaban recuperar el año, el mes, 
		 * el día de una fecha, etc. Actualmente estos métodos son deprecated.
		 */
		System.out.println("MÉTODOS DEPRECATED DE Date");
		//Obtener el año con metodo deprecated
		System.out.println(fechaActual.getYear());
		//Obtener el mes con metodo deprecated
		System.out.println(fechaActual.getMonth());
		//Obtener el dia con metodo deprecated
		System.out.println(fechaActual.getDay());
		//Obtener horas con metodo deprecated
		System.out.println(fechaActual.getHours());
		//Obtener minutos con metodo deprecated
		System.out.println(fechaActual.getMinutes());
		//Otener segundos con metodo deprecated
		System.out.println(fechaActual.getSeconds());
		
		/*
		 * En las últimas versiones de Java, se dispone de la clase Java Calendar.
		 * El problema de la clase Calendar es que no se puede instanciar directamente,
		 * sino que tenemos dos formas diferentes:
		 * 1- Mediante el metodo estático getInstance() de la clase Calendar.
		 * 2- Mediante una subclase de Calendar llamada GregorianCalendar.
		 */
		
		//Instanciacion mediante getInstance()
		Calendar fechaGetInstance = Calendar.getInstance();
		System.out.println("\nMETODO getInstance()");
		/*
		 * Para extraer los datos necesarios hacemos uso de uno único metodo get(), 
		 * al cual le pasamos por parametro el campo que queremos conseguir.
		 */
		//Extracción del año actual
		System.out.println(fechaGetInstance.get(Calendar.YEAR));
		//Extracción del mes actual
		System.out.println(fechaGetInstance.get(Calendar.MONTH));
		//Extracción del día del mes actual
		System.out.println(fechaGetInstance.get(Calendar.DAY_OF_MONTH));
		//Extracción del día de la semana actual
		System.out.println(fechaGetInstance.get(Calendar.DAY_OF_WEEK));
		/*
		 * Existen muchos campos, los cuales podemos ver en el siguiente enlace 
		 * -> https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
		 */
		
		//Instanciación mediante GregorianCalendar
		Calendar fechaGregorian = new GregorianCalendar();
		System.out.println("\nGREGORIAN CALENDAR");
		//Dia de la semana
		System.out.println(fechaGregorian.get(Calendar.WEEK_OF_YEAR));
		//Hora
		System.out.println(fechaGregorian.get(Calendar.HOUR));
		//Año actual
		System.out.println(fechaGregorian.get(Calendar.YEAR));
		//Mes actual
		System.out.println(fechaGregorian.get(Calendar.MONTH));
		//Dia del mes
		System.out.println(fechaGregorian.get(Calendar.DAY_OF_MONTH));
		/*
		 * Podemos ver la totalidad de campos disponibles para extraer en 
		 * -> https://docs.oracle.com/javase/7/docs/api/java/util/GregorianCalendar.html
		 */
		
		/*
		 * También tenemos la opción de crear fechas manualmente.
		 * Ya sea directamente en el constructor o mediante el uso del metodo set()
		 */
		System.out.println("\nCREACION FECHAS MANUALMENTE");
													//YYYY, MM, DD
		Calendar fechaManual = new GregorianCalendar(2015, 01, 22); //Fecha manual directamente en el constructor
		Calendar fechaManualConSet = new GregorianCalendar(); fechaManualConSet.set(2015, 01, 22); //Fecha manual con set()
		System.out.println(fechaManual.get(Calendar.DATE) + "/" + fechaManual.get(Calendar.MONTH) + "/" + fechaManual.get(Calendar.YEAR));
		
		/*
		 * Pero...¿Qué pasaria si insertamos manualmente una fecha que no fuese válida?
		 */
		Calendar fechaInvalida = new GregorianCalendar(2015, 01, 31); //31 de Febrero de 2015
		System.out.println("Al introducir 31 de Febrero de 2015, nos modifica a 3 de Marzo de 2015");
		System.out.println(fechaInvalida.getTime());
		
		/*
		 * Para evitar que se introduzcan fechas invalidas en nuestro programa debemos desactivar
		 * la opcion setLenient() mediante un DateFormat.
		 */
		System.out.println("Con setLenient(false) evitamos fechas invalidas al lanzar una excepción");
		try {
			   String fechaInvalidaConLenient = "32/02/2015"; //Debemos de introducir la fecha como cadena
			   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			   dateFormat.setLenient(false); //Desactivamos el lenient
			   Date fecha = dateFormat.parse(fechaInvalidaConLenient); //Formateamos la fecha
			   System.out.println("Fecha válida: "+fecha.toString());
			  } catch (ParseException e) {
			   System.out.println("Fecha no válida");
		}

		/*
		 * Operaciones con Fechas
		 */
		System.out.println("\nOPERACIONES CON FECHAS");
		System.out.println("Partimos de la fecha " + fechaManual.getTime());
		//Sumar dias a una fecha
		fechaManual.add(Calendar.DAY_OF_MONTH, 2); //Sumamos dos días al día del mes actual
		System.out.println("Sumamos dos días al día del mes actual");
		System.out.println(fechaManual.getTime());
		//Restar dias a una fecha
		fechaManual.add(Calendar.DAY_OF_MONTH, -5); //Restamos cinco días al día del mes actual
		System.out.println("Restamos cinco dias al día del mes actual");
		System.out.println(fechaManual.getTime());
		//Tambien podemos sumar horas, meses, años, etc...
		/*
		 * Cabe decir que existe otro metodo para operar con fechas llamado roll(). La principal
		 * diferencia con add() es que cuando se produce un intercambio de unidades, el método
		 * roll() no incrementa la unidad inmediatamente superior. Por ejemplo si sumamos al 28 de Febrero 
		 * 5 días, el campo días pasará a ser 5, pero el campo mes no se verá afectado.
		 * fechaManual.roll(int campo, int incremento);
		 */
		
		
		/*
		 * Clase DateFormat
		 * Esta clase nos permitirá dar formato a la salida de fechas por pantalla
		 * 
		 */
		System.out.println("\nCLASE DATEFORMAT");
		//Array con los diferentes formatos de fecha
		DateFormat[] dfa = new DateFormat[6];
		//Formato estándar		
        dfa[0] = DateFormat.getInstance();
        //Fecha con guiones, mes en letra y sin tiempo
        dfa[1] = DateFormat.getDateInstance();
        //Formato corto de fecha
        dfa[2] = DateFormat.getDateInstance(DateFormat.SHORT);
        //Formato medio de fecha
        dfa[3] = DateFormat.getDateInstance(DateFormat.MEDIUM);
        //Formato largo de fecha
        dfa[4] = DateFormat.getDateInstance(DateFormat.LONG);
        //Formato completo de fecha
        dfa[5] = DateFormat.getDateInstance(DateFormat.FULL);
        //Imprimimos los diferentes formatos
        for (DateFormat df : dfa){
            System.out.println(df.format(fechaManual.getTime()));
        }
	
		/*
		 * La clase Locale puede ser usada conjuntamente con DateFormat para
		 * que el formato de salida de una fecha sea específico para
		 * una localización determinada.
		 */
	    System.out.println("\nUSO DE CLASE LOCALE");
	    //Creamos un objeto Locale para localización italiana
		Locale locIt = new Locale("it", "IT");
		//Creamos un objeto DateFormat de tipo completo y usamos el locale italiano previo
		DateFormat dfIt = DateFormat.getDateInstance(DateFormat.FULL, locIt);
		//Aplicamos el formato a nuestra fecha actual
		System.out.println(dfIt.format(fechaActual));
		
		//Creamos un objeto Locale para localización estadounidense
		Locale locUs = new Locale("en", "US");
		//Creamos un objeto DateFormat de tipo completo y usamos el locale anterior
		DateFormat dfUs = DateFormat.getDateInstance(DateFormat.FULL, locUs);
		//Le damos formato
		System.out.println(dfUs.format(fechaActual));
		
		
//		Para ver las Locales disponibles disponemos de getAvailableLocales()
//		 Locale[] locales = Locale.getAvailableLocales();
//		 for (int i = 0; i < locales.length; i++) {
//			System.out.println(i + ":" + locales[i]);
//		 }

	}
}
