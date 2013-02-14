package com.epam.testapp.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public final class PagingTag extends TagSupport {

	private static final long serialVersionUID = 6472091214170606308L;

	private String url = "";
	private String pageId;
	private int currentPageNumber;
	private int range;
	private int results;
	private int countItems= 7;

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public void setResults(int results) {
		this.results = results;
	}	

	public void setCountItems(int countItems) {
		this.countItems = countItems;
	}

	@Override
	public int doStartTag() throws JspException {
		int countPages = (results + range - 1) / range;
		int start = 1, end = countPages;
		
		if (range >= 0 && currentPageNumber <= countPages) {
			if (url.indexOf("?") != -1) {
				url += "&";
			} else {
				url += "?";
			}
			url += pageId;
			
			if (countItems > countPages) {
				countItems = countPages;
			}		
			
			if (currentPageNumber <= countItems / 2) {
				end = countItems;
			} else if (currentPageNumber > countPages - countItems / 2) {
				start = countPages - countItems + 1;				
			} else {
				start = currentPageNumber - countItems / 2;
				end = currentPageNumber + countItems / 2;
			}			

			try {
				JspWriter out = pageContext.getOut();
				out.write(buildPager(start, end, countPages, url));
			} catch (IOException e) {
				throw new JspException(e.getMessage());
			}
		}

		return SKIP_BODY;
	}
	
	private String buildPager(int start, int end, int countPages, String url) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("<div>");
		if (currentPageNumber - 1 != 0) {
			builder.append("<a href='");
			builder.append(url);
			builder.append("=");
			builder.append(currentPageNumber - 1);
			builder.append("'>");
			builder.append("Prev");
			builder.append("</a>");
		} else {
			builder.append("Prev");
		}
		builder.append(" ");			
		for (int i = start; i <= end; i++) {
			if (i != currentPageNumber) {
				builder.append("<a href='");
				builder.append(url);
				builder.append("=");
				builder.append(i);
				builder.append("'>");
				builder.append(i);
				builder.append("</a>");
			} else {
				builder.append(i);
			}
			builder.append(" ");
		}
		if (currentPageNumber != countPages) {
			builder.append("<a href='");
			builder.append(url);
			builder.append("=");
			builder.append(currentPageNumber + 1);
			builder.append("'>");
			builder.append("Next");
			builder.append("</a>");
		} else {
			builder.append("Next");
		}
		builder.append("</div>");
		
		return builder.toString();
	}
}
