import java.util.*;


public class hw_6 {
    /*
        + Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
        + Создать множество ноутбуков.
        + Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map. Например: “Введите цифру, соответствующую необходимому критерию:
            + 1 - ОЗУ
            + 2 - Объем ЖД
            + 3 - Операционная система
            + 4 - Цвет …
        + Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
        + Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.
    */
    public static void main( String[] args ) {
        System.out.println( "\nПрограмма запрашивает у пользователя критерии для выбора ноутбука" );

        // У меня всегда было мнение что в магазинах сборки делаю именно так...
        Set<Notebook> catalog = generator( 50 );

        // Тест сравнение объектов Notebook на аналог и на лучше, без учета цвета
        // testNotebookComparing();

        // Вывод списока всех ноутбуков c нумерацией
        // printFullCatalog( catalog );

        System.out.println();

        HashMap<String, String> params = getSearchParams();

        int ram = Integer.parseInt( params.get( "ram") );
        int hdd = Integer.parseInt( params.get( "hdd") );
        String os = params.get( "os");
        String color = params.get( "color");

        int i = 1;
        boolean flag = false;
        for ( Notebook element : catalog ) { // Вывод списка
            Notebook search = new Notebook( ram, hdd, os, color );
            if ( element.equals( search ) || element.better( search ) ) {
                System.out.println( i++ + ". " + element );
                flag = true;
            }
        }

        if ( !flag ) { // Если не вывели ни 1 ноутбука сообщить...
            System.out.println( "К сожалению ноутбуков с такой канфигурацией нет в нашем каталоге..." );
        }

        System.out.println();
    }


    public static Set<Notebook> generator( int count ) { // Генератор множества
        Set<Notebook> catalog = new HashSet<>();

        int ram, hdd;
        String os, color;

        int[] rams = {2, 4, 6, 8, 10, 12, 14, 16};
        int[] hdds = {120, 180, 240, 360, 480, 540, 720, 960};
        String[] oss = {"noOS", "Windows 7", "Windows 10", "Windows 11", "Linux"};
        String[] colors = {"RED", "ORANGE", "YELLOW", "GREEN", "BLUE", "INDIGO", "VIOLET"};

        for (int i = 0; i < count; i++) {
            ram = rams[new Random().nextInt(rams.length)];
            hdd = hdds[new Random().nextInt(hdds.length)];
            os = oss[new Random().nextInt(oss.length)];
            color = colors[new Random().nextInt(colors.length)];

            Notebook n = new Notebook( ram, hdd, os, color );
            catalog.add( n );
        }

        return catalog;
    }


    public static HashMap<String, String> getSearchParams() {
        HashMap<String, String> params = new HashMap<>();

        Scanner iScanner = new Scanner( System.in );
        System.out.println("Для вывода списка подходящих ноутбков введите значение и нажмите 'Enter', чтобы подтвердить выбор.");

        int ram, hdd;
        String os, color;

        while ( true ) { //  Запрос ОЗУ
            System.out.print("Введите число, объем оперативной памяти (до 16): ");
            ram = iScanner.nextInt();
            if ( ram <= 16 )
                break;
        }

        while ( true ) { // Запрос HDD
            System.out.print("Введите число, объем жесткого диска (до 960): ");
            hdd = iScanner.nextInt();
            if ( hdd <= 960 )
                break;
        }

        while ( true ) { // Запрос OS
            String[] oss = {"noOS", "Windows", "Linux"};

            System.out.print("\nСписок доступных OS:\n");
            System.out.print( "\t0. Любая OS\n");
            for (int i = 0; i < oss.length; i++) {
                System.out.print( "\t" + (i + 1) + ". " + oss[i] + "\n");
            }
            System.out.print("Введите число, для выбора OS из списка: ");

            int number = iScanner.nextInt();
            if ( number == 0 ) {
                os = "";
                break;
            }

            if ( number > 0 && ( number - 1 ) < oss.length ) {
                os = oss[number - 1];
                break;
            }
        }

        while ( true ) { // Запрос цвета
            String[] colors = {"RED", "ORANGE", "YELLOW", "GREEN", "BLUE", "INDIGO", "VIOLET"};

            System.out.print("\nСписок доступных цветов:\n");
            System.out.print( "\t0. Любой цвет\n");
            for (int i = 0; i < colors.length; i++) {
                System.out.print( "\t" + (i + 1) + ". " + colors[i] + "\n");
            }
            System.out.print("Введите число, для выбора цвета из списка: ");

            int number = iScanner.nextInt();
            if ( number == 0 ) {
                color = "";
                break;
            }

            if ( number > 0 && (number - 1) < colors.length) {
                color = colors[number - 1];
                break;
            }
        }
        System.out.println();

        iScanner.close();

        params.put( "ram", Integer.toString( ram ) );
        params.put( "hdd", Integer.toString( hdd ) );
        params.put( "os", os );
        params.put( "color", color );

        return params;
    }


    public static void printFullCatalog ( Set<Notebook> catalog ) { // Вывод списока ноутбуков c нумерацией
        int i = 1;
        for (Notebook element : catalog) {
            System.out.println( i++ + ". " + element );
        }
    }


    public static void testNotebookComparing() {
        Notebook a = new Notebook( 8, 120, "", "" );
        Notebook b = new Notebook( 16, 240, "", "" );
        Notebook c = new Notebook( 8, 120, "", "" );
        System.out.println( a.equals( c ) ); // Аналог
        System.out.println( b.equals( c ) );
        System.out.println( a.better( c ) );
        System.out.println( b.better( c ) ); // Лучше
    }
}