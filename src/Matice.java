import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
/**
 * Třída, která reprezentuje matice
 * dimenze m, dimenze n, pole dat data
 * @author Michaela Sedlarova 
 * @version 1.0
 * @see TypMatice
 * @see Matrix
 */
public class Matice<T extends Number> implements Matrix{
    private final int m;
    private final int n;
    private final Object data[][];
    private final Class<T> type;

    /** první přetížený konstruktor Matice
     * Vytvoří nulovou matici
     *
     * @param m velikost první dimenze
     * @param n velikost druhé dimenze
     *
     */
    public Matice(Class<T> type, int m, int n) {
        this.m = m;
        this.n = n;
        data=new Object[m][n];
        this.type = type;
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                this.nastavPrvek(i, j,0 );
            }
        }

    }

    /** druhý přetížený konstruktor Matice
     * Vytvoří matici podle parametru  TypMatice
     *
     * @param type typ matice
     * @param m velikost první dimenze
     * @param n velikost druhé dimenze
     * @param typ udává, jakou matici chceme vytvořit
     * @see TypMatice
     */
    public Matice(Class<T> type ,int m, int n, TypMatice typ) {
        this.m = m;
        this.n = n;
        this.type = type;
        data=new Object[m][n];
        if (typ == TypMatice.NULOVA) {
            int i, j;
            for (i = 0; i < m; i++) {
                for (j = 0; j < n; j++) {
                    this.nastavPrvek(i, j,0 );
                }
            }
        }
        if (typ == TypMatice.JEDNOTKOVA) {
            int i, j;
            for (i = 0; i < m; i++) {
                for (j = 0; j < n; j++) {
                    if (i == j) this.nastavPrvek(i, j,1 );
                    else this.nastavPrvek(i, j,0 );
                }
            }
        }
        if (typ == TypMatice.RANDOM) {
            Random rand = new Random();

            int i, j;
            for (i = 0; i < m; i++) {
                for (j = 0; j < n; j++) {
                    this.nastavPrvek(i, j,rand.nextInt(10) );

                }
            }
        }
    }

    /** Nastavý prvek matice na souřadnicích i j
     *
     * @param i první souřadnice
     * @param j druhá souřadnice
     * @param cislo cislo, které chceme zapsat
     */
    public void nastavPrvek(int i, int j,Number cislo) {
        Integer a= 0;
        Double b=0.0;
        Long c= 0L;
        Float d =0.0f;
        Short e =0;

        if(a.getClass() == type){
            int vysledek = cislo.intValue();
            data[i][j] = vysledek;
        }else if(b.getClass() == type){
            double vysledek = cislo.doubleValue();
            data[i][j] = vysledek;
        }else if(c.getClass() == type){
            long vysledek = cislo.longValue();
            data[i][j] = vysledek;
        }else if(d.getClass() == type){
            float vysledek = cislo.floatValue();
            data[i][j] = vysledek;
        }else if(e.getClass() == type){
            short vysledek = cislo.shortValue();
            data[i][j] = vysledek;
        }else{
            Object vysledek = cislo;
            data[i][j] = vysledek;
        }


    }

    /** Vrátí velikost dimenze m
     *
     * @return m velikost dimenze m
     */
    @Override
    public int getM() {
        return m;
    }

    /** Vrátí velikost dimenze n
     *
     * @return m velikost dimenze n
     */
    @Override
    public int getN() {
        return n;
    }

    /** Vrátí prvek na souradnicích
     *
     * @param i první souřadnice
     * @param j druhá souřadnice
     * @return m velikost dimenze m
     */
    public T vratPrvek(int i, int j) {
        return (T) data[i][j];
    }

    /** Privátní metoda, kterou využívá metoda toString()
     * vytvoří String z dat matice
     *
     * @return vypis vypis matice
     */
    private String vypisMatice() {
        StringBuilder str = new StringBuilder();
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                str.append(data[i][j] + " ");
            }
            str.append("\n");
        }
        String vypis = str.toString();
        return vypis;
    }

    /** Vrací String s informacemi o matici
     *
     * @return string
     */
    @Override
    public String toString() {
        return "Matice{" + "m=" + m + ", n=" + n + "} \n" + vypisMatice();
    }

    /** Zadání prvků do matice
     *
     */
    public void zadatData() {
        int i, j;
        double cislo = 0;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                boolean pokracovat = true;
                while (pokracovat) {
                    try {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Zadejte prvek[" + i + "][" + j + "]:");
                        cislo = scanner.nextDouble();
                        pokracovat = false;
                    } catch (Exception x) {
                        System.out.println("Musíte zadat číslo v rozsahu double");
                    }
                }

                this.nastavPrvek(i,j, cislo);
            }
        }
    }

    /** Vynásobí matici skalárem a z výsledku vytvoří novou matici
     *
     * @param x skalár, kterým násobíme
     * @return nova vrátí novou matici
     */
    @Override
    public Matice<T> nasobitSkalarem(int x) {


        Matice<T> nova = new Matice(type,m, n);
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                // T n =(T) data[i][j];

                Object a = data[i][j];

                Double val = null;
                if (a instanceof Number) {
                    val = ((Number) a).doubleValue();
                }
                double vysledek =val * x;


                nova.nastavPrvek(i, j,vysledek);
            }
        }
        return nova;
    }

    /** Sečte dvě matice a vytvoří z nich novou matici
     *
     * @param x matice, se kterou budeme sčítat
     * @return nova vrátí novou matici
     */
    @Override
    public Matice<T> plus(Matice x) {
        Matice<T> nova = new Matice(type,m, n);
        if (m != x.getM() || n != x.getN()) {
            System.out.println("Matice nejsou stejného rozměru, nejdou sčítat.");
            return nova;
        }
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                Object a = data[i][j];
                double val = ((Number) a).doubleValue();
                Object b = x.vratPrvek(i, j);
                double val2 = ((Number) a).doubleValue();

                nova.nastavPrvek(i, j, val + val2);
            }
        }
        return nova;
    }

    /** Odečte dvě matice a vytvoří z nich novou matici
     *
     * @param x matice, kterou použijeme při odečítání
     * @return nova vrátí novou matici
     */
    @Override
    public Matice<T> minus(Matice x) {
        Matice<T> nova = new Matice(type,m, n);
        if (m != x.getM() || n != x.getN()) {
            System.out.println("Matice nejsou stejného rozměru, nejdou odečítat.");
            return nova;
        }
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                Object a = data[i][j];

                double val = ((Number) a).doubleValue();
                Object b = x.vratPrvek(i, j);
                double val2 = ((Number) b).doubleValue();

                nova.nastavPrvek(i, j, val - val2);
            }
        }
        return nova;
    }

    /** Vytvoří novou transponovanou matici
     *
     * @return nova vrátí novou matici
     */
    public Matice<T> transponovana() {
        Matice<T>nova = new Matice(type,n, m);
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                Object a = data[i][j];
                double val = ((Number) a).doubleValue();
                nova.nastavPrvek(j, i, val);
            }
        }
        return nova;
    }

    /** Vynásobí dvě matice a vytvoří z nich novou matici
     *
     * @param x matice, se kterou chceme násobit
     * @return nova vrátí novou matici
     */
    @Override
    public Matice<T> krat(Matice x) {
        Matice<T> nova = new Matice(type,m, x.getN());
        if (n != x.getM()) {
            System.out.println("Matice se nedají násobit: M první matice: " + m + ", N druhé matice : " + x.getN());
            return nova;
        }
        int i, j, k;
        double cislo = 0;
        for (i = 0; i < m; i++) {
            for (j = 0; j < x.getN(); j++) {
                for (k = 0; k < n; k++) {
                    Object a = data[i][j];
                    double val = ((Number) a).doubleValue();
                    Object b = x.vratPrvek(i, j);
                    double val2 = ((Number) a).doubleValue();


                    cislo += val *val2;
                }
                nova.nastavPrvek(i, j, cislo);
                cislo = 0;
            }
        }
        return nova;
    }

    /** Zapíše matici do souboru
     *
     * @param jmenosouboru určuje cestu k souboru
     */
    public void doSouboru(String jmenosouboru) {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        try {
            fileWriter = new FileWriter(jmenosouboru);
            printWriter = new PrintWriter(fileWriter);
            printWriter.print(vypisMatice());
            printWriter.close();
        } catch (Exception x) {
            System.out.println("Něco se pokazilo:" + x);
        }
    }

    /** Porovná 2 matice a určí zda jsou stejné, či ne
     *
     * @param x matice, se kterou chceme porovnávat
     * @return true jestli jsou obě matice stejné
     * @return false jestli nejsou obě matice stejné
     */
    public boolean equal(Matice x) {
        if (m != x.getM() || n != x.getN()) {
            return false;
        }
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (data[i][j] != x.vratPrvek(i, j)){
                    return false;
                }
            }
        }
        return true;
    }

    /** Vytvoří dvě vlákna
     * v prvním matici vynásobí druhoumaticí, a výsledek zapíše do souboru
     * v druhém udělá transpozici matice a zapíše ji do souboru
     *
     * @param x cislo, kterým chceme matici násobit
     * @param jmenoSouboruNasobeni cesta k souboru, do ktereho chceme dát výsledek nasobeni
     * @param jmenoSouboruTrans cesta k souboru, do ktereho chceme dát výsledek transpozice
     */
    public void vlakna(int x , String jmenoSouboruNasobeni, String jmenoSouboruTrans){
        Thread nasobeni = new Thread (() -> { (this.nasobitSkalarem(x)).doSouboru(jmenoSouboruNasobeni);});
        Thread transpon = new Thread (() -> { (this.transponovana()).doSouboru(jmenoSouboruTrans);});
        nasobeni.start();
        transpon.start();

    }

}