package introduction;

/**
 * @author Sasha
 *
 */
public class TriangleUtils {

	/**
	 * Задача о треугольнике
	 * 
	 * Вам даны длинны трех отрезков: a, b, c. Нужно вернуть true, если они
	 * могут быть сторонами треугольника и false, если не могут.
	 *
	 */

	public boolean isTriangle(int a, int b, int c) throws IllegalArgumentException {
		validateArguments(a, b, c);
		return (a < (b + c) && b < (a + c) && c < (a + b));
	}

	/**
	 * Вам даны длинны трех сторон треугольника: a, b, c. Необходимо вычислить
	 * площадь треугольника.
	 * 
	 * @throws IllegalArgumentException
	 */

	public double getTriangleArea(int a, int b, int c) throws IllegalArgumentException {
		validateArguments(a, b, c);
		double semiperimeter = (a + b + c) / 2;
		return Math.sqrt(semiperimeter * (semiperimeter - a) * (semiperimeter - b) * (semiperimeter - c));
	}

	/**
	 * Проверяет не являются ли аргументы методов (длины сторон треугольника)
	 * отрицательными или нулевыми значениями. В положительном случае бросает
	 * исключение.
	 * 
	 * @throws IllegalArgumentException
	 */
	private static void validateArguments(int a, int b, int c) throws IllegalArgumentException {
		if (a <= 0 || b <= 0 || c <= 0) {
			throw new IllegalArgumentException();
		}
	}

	public static void main(String[] args) {
		TriangleUtils tu = new TriangleUtils();
		System.out.println("isTriangle(2, 3, 19) == " + tu.isTriangle(2, 3, 19));
		System.out.println("isTriangle(7, 17, 19) == " + tu.isTriangle(7, 17, 19));
		System.out.println("---------");
		System.out.printf("getTriangleArea(7, 17, 19) == %.2f", tu.getTriangleArea(7, 17, 19));
	}
}
