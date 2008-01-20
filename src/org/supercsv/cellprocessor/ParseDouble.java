package org.supercsv.cellprocessor;

import org.supercsv.cellprocessor.ift.DoubleCellProcessor;
import org.supercsv.exception.SuperCSVException;
import org.supercsv.util.CSVContext;

/**
 * Convert a string to a double
 * 
 * @author Kasper B. Graversen
 */
public class ParseDouble extends CellProcessorAdaptor {

	public ParseDouble() {
		super();
	}

	public ParseDouble(final DoubleCellProcessor next) {
		super(next);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(final Object value, final CSVContext context) throws SuperCSVException {
		final Double result;
		if(value instanceof Double) {
			result = (Double) value;
		}
		else if(value instanceof String) {
			try {
				result = new Double((String) value);
			}
			catch(NumberFormatException e) {
				throw new SuperCSVException("Parser error", context, e);
			}
		}
		else {
			throw new SuperCSVException("Can't convert \"" + value
					+ "\" to double. Input is not of type Double nor type String, but of type "
					+ value.getClass().getName(), context);
		}

		return next.execute(result, context);
	}
}
