public class Notebook {
    int ram;
    int hdd;
    String os;
    String color;


    public Notebook( int ram, int hdd, String os, String color ) {
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }


    public String toString() {
        return String.format( "[ram: %d ГБ, ssd: %d ГБ, OS: %s, color: %s]", ram, hdd, os, color );
    }


    public boolean equals( Object other ) { // Выбор моделей аналогов
        Notebook n = ( Notebook ) other;
        if ( !n.os.equals("") && !n.color.equals("") )
            return ( ( this.ram == n.ram ) &&
                     ( this.hdd == n.hdd ) &&
                     ( this.os.split( " " )[0].equals( n.os ) ) &&
                     ( this.color.equals( n.color ) ) );

        if ( !n.os.equals("") && n.color.equals("") )
            return ( ( this.ram == n.ram ) &&
                     ( this.hdd == n.hdd ) &&
                     ( this.os.split( " " )[0].equals( n.os ) ) );

        if ( n.os.equals("") && !n.color.equals("") )
            return ( ( this.ram == n.ram ) &&
                     ( this.hdd == n.hdd ) &&
                     ( this.color.equals( n.color ) ) );

        // Нет цвета нет OS
        return ( this.ram == n.ram ) &&
               ( this.hdd == n.hdd );
    }


    public boolean better( Object other ) { // Выбор моделей лучше
        Notebook n = ( Notebook ) other;

        if ( !n.os.equals("") && !n.color.equals("") )
            return ( ( ( this.ram > n.ram && this.hdd > n.hdd ) ||
                       ( this.ram > n.ram && this.hdd == n.hdd ) ||
                       ( this.ram == n.ram && this.hdd > n.hdd ) ) &&
                    ( this.os.split( " " )[0].equals( n.os ) ) &&
                    ( this.color.equals( n.color ) ) ); // А вот цвет, это ОЧЕНЬ ВАЖНО )))

        // Нет цвета
        if ( !n.os.equals("") && n.color.equals("") )
            return ( ( ( this.ram > n.ram && this.hdd > n.hdd ) ||
                       ( this.ram > n.ram && this.hdd == n.hdd ) ||
                       ( this.ram == n.ram && this.hdd > n.hdd ) ) &&
                     ( this.os.split( " " )[0].equals( n.os ) ) );

        // Нет OS
        if ( n.os.equals("") && !n.color.equals("") )
            return ( ( ( this.ram > n.ram && this.hdd > n.hdd ) ||
                       ( this.ram > n.ram && this.hdd == n.hdd ) ||
                       ( this.ram == n.ram && this.hdd > n.hdd ) ) &&
                     ( this.color.equals( n.color ) ) ); // А вот цвет, это ОЧЕНЬ ВАЖНО )))


        // Нет цвета нет OS
        return ( ( this.ram > n.ram && this.hdd > n.hdd ) ||
                 ( this.ram > n.ram && this.hdd == n.hdd ) ||
                 ( this.ram == n.ram && this.hdd > n.hdd ) );
    }
}