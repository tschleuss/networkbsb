package org.furb.bsb.resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.furb.bsb.model.Matrix;
import org.furb.bsb.utils.Setup;

/**
 * Classe responsavel por ler e popular a base de conhecimento para que o
 * programa possa treinar.
 * 
 * @author Thyago Schleuss
 * @author Luiz Diego Aquino
 * @author Luiz Roberto Leicht
 * @since 20/04/2010
 */
public class KnowledgeLoader {

	public KnowledgeLoader() {
		super();
	}

	/**
	 * Le o arquivo 'knowledgeBase.txt' e carrega a matrix de valores
	 */
	public List<Matrix> load() {
		List<Matrix> retList = null;

		try {

			InputStream in = this.getResource("/org/furb/bsb/resource/knowledgeBase.txt", KnowledgeLoader.class);
			InputStreamReader reader = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(reader);
			StringTokenizer tokenizer = null;

			retList = new ArrayList<Matrix>();
			double[][] matrix = new double[Setup.ROW_SIZE][Setup.COL_SIZE];
			String line = null;
			String content = null;
			int rows = 0;
			int cols = 0;

			while ((line = br.readLine()) != null) 
			{
				if (!line.startsWith("--")) 
				{
					if (!line.trim().isEmpty()) 
					{
						tokenizer = new StringTokenizer(line, ",");

						while (tokenizer.hasMoreTokens()) {
							content = tokenizer.nextToken();
							matrix[rows][cols] = Double.valueOf(content.trim());
							cols++;
						}

						cols = 0;
						rows++;
					} else {
						
						if (rows != Setup.ROW_SIZE && cols != Setup.COL_SIZE
								&& tokenizer != null) {
							throw new Exception("Inconsistência no arquivo !");
						} else {
							if (tokenizer != null) {
								retList.add(new Matrix(matrix)
										.convertInColumn());
								matrix = new double[Setup.ROW_SIZE][Setup.COL_SIZE];
								rows = 0;
								cols = 0;
							}
						}
					}
				}
			}

			retList.add(new Matrix(matrix).convertInColumn());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retList;
	}

	public InputStream getResource(final String resourceName, final Class<?> caller) 
	{
		final String resource;
		if (resourceName.startsWith("/")) {
			resource = resourceName.substring(1);
		} else {
			final Package callerPackage = caller.getPackage();
			if (callerPackage != null) {
				resource = callerPackage.getName().replace('.', '/') + '/'
						+ resourceName;
			} else {
				resource = resourceName;
			}
		}
		final ClassLoader threadClassLoader = Thread.currentThread()
				.getContextClassLoader();
		if (threadClassLoader != null) {
			final InputStream is = threadClassLoader
					.getResourceAsStream(resource);
			if (is != null) {
				return is;
			}
		}

		final ClassLoader classLoader = caller.getClassLoader();
		if (classLoader != null) {
			final InputStream is = classLoader.getResourceAsStream(resource);
			if (is != null) {
				return is;
			}
		}

		return ClassLoader.getSystemResourceAsStream(resource);
	}
}
