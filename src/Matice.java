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
public class Matice implements Matrix{
    int m;
    int n;
    double data[][];

    /** první přetížený konstruktor Matice
     * Vytvoří nulovou matici
     *
     * @param m velikost první dimenze
     * @param n velikost druhé dimenze
     */
    public Matice(int m, int n) {
        this.m = m;
        this.n = n;
        this.data = new double[m][n];
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                data[i][j] = 0;
            }
        }
    }

    /** druhý přetížený konstruktor Matice
     * Vytvoří matici podle parametru  TypMatice
     *
     * @param m velikost první dimenze
     * @param n velikost druhé dimenze
     * @param typ udává, jakou matici chceme vytvořit
     * @see TypMatice
     */
    public Matice(int m, int n, TypMatice typ) {
        this.m = m;
        this.n = n;
        this.data = new double[m][n];
        if (typ == TypMatice.NULOVA) {
            int i, j;
            for (i = 0; i < m; i++) {
                for (j = 0; j < n; j++) {
                    data[i][j] = 0;
                }
            }
        }
        if (typ == TypMatice.JEDNOTKOVA) {
            int i, j;
            for (i = 0; i < m; i++) {
                for (j = 0; j < n; j++) {
                    if (i == j) data[i][j] = 1;
                    else data[i][j] = 0;
                }
            }
        }
        if (typ == TypMatice.RANDOM) {
            Random rand = new Random();

            int i, j;
            for (i = 0; i < m; i++) {
                for (j = 0; j < n; j++) {
                    data[i][j] = rand.nextInt(10);
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
    public void nastavPrvek(int i, int j, double cislo) {
        data[i][j] = cislo;
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
    public double vratPrvek(int i, int j) {
        return data[i][j];
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
                data[i][j] = cislo;
            }
        }
    }

    /** Vynásobí matici skalárem a z výsledku vytvoří novou matici
     *
     * @param x skalár, kterým násobíme
     * @return nova vrátí novou matici
     */
    @Override
    public Matice nasobitSkalarem(int x) {
        Matice nova = new Matice(m, n);
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                nova.nastavPrvek(i, j, data[i][j] * x);
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
    public Matice plus(Matice x) {
        Matice nova = new Matice(m, n);
        if (m != x.getM() || n != x.getN()) {
            System.out.println("Matice nejsou stejného rozměru, nejdou sčítat.");
            return nova;
        }
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                nova.nastavPrvek(i, j, data[i][j] + x.vratPrvek(i, j));
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
    public Matice minus(Matice x) {
        Matice nova = new Matice(m, n);
        if (m != x.getM() || n != x.getN()) {
            System.out.println("Matice nejsou stejného rozměru, nejdou odečítat.");
            return nova;
        }
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                nova.nastavPrvek(i, j, data[i][j] - x.vratPrvek(i, j));
            }
        }
        return nova;
    }

    /** Vytvoří novou transponovanou matici
     *
     * @return nova vrátí novou matici
     */
    public Matice transponovana() {
        Matice nova = new Matice(n, m);
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                nova.nastavPrvek(i, j, data[j][i]);
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
    public Matice krat(Matice x) {
        Matice nova = new Matice(m, x.getN());
        if (n != x.getM()) {
            System.out.println("Matice se nedají násobit: M první matice: " + m + ", N druhé matice : " + x.getN());
            return nova;
        }
        int i, j, k;
        double cislo = 0;
        for (i = 0; i < m; i++) {
            for (j = 0; j < x.getN(); j++) {
                for (k = 0; k < n; k++) {
                    cislo += data[i][k] * x.vratPrvek(k, j);
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