/** Interface pro Matice
 *
 * @author Michaela Sedlarova
 */
public interface Matrix {
    public int getN();
    public int getM();
    public Matice nasobitSkalarem(int x);
    public Matice plus(Matice x);
    public Matice minus(Matice x);
    public Matice krat(Matice x);
}
