/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-12-17 19:09:42 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import fr.da2i.Commandes;
import fr.da2i.Fournisseurs;
import java.util.List;

public final class ListerCommandes_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"ErrorPage.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html lang=\"fr\">\n");
      out.write("<head>\n");
      out.write("    <title>Lister les commandes</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("</head>\n");
      out.write("<body class=\"text-center text-white\">\n");
      out.write("    <div class=\"c-container d-flex w-100 h-100 p-3 mr-5 ml-5 flex-column\">\n");
      out.write("        <header class=\"masthead mb-auto\">\n");
      out.write("            <div class=\"inner\">\n");
      out.write("                <h3 class=\"masthead-brand\">Projet JPA</h3>\n");
      out.write("                <nav class=\"nav nav-masthead justify-content-center\">\n");
      out.write("                    <a class=\"nav-link active\" href=\"ListerCommandes.jsp\">Commandes</a>\n");
      out.write("                    <a class=\"nav-link\" href=\"ListerFournisseurs.jsp\">Fournisseurs</a>\n");
      out.write("                    <a class=\"nav-link\" href=\"ListerCommandesProduits.jsp\">Commandes/Fournisseurs/Produits</a>\n");
      out.write("                </nav>\n");
      out.write("            </div>\n");
      out.write("        </header>\n");
      out.write("        ");
      fr.da2i.DAOCommandes daoCommande = null;
      synchronized (application) {
        daoCommande = (fr.da2i.DAOCommandes) _jspx_page_context.getAttribute("daoCommande", javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        if (daoCommande == null){
          daoCommande = new fr.da2i.DAOCommandes();
          _jspx_page_context.setAttribute("daoCommande", daoCommande, javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        }
      }
      out.write("\n");
      out.write("        ");
      fr.da2i.DAOFournisseurs daoFournisseurs = null;
      synchronized (application) {
        daoFournisseurs = (fr.da2i.DAOFournisseurs) _jspx_page_context.getAttribute("daoFournisseurs", javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        if (daoFournisseurs == null){
          daoFournisseurs = new fr.da2i.DAOFournisseurs();
          _jspx_page_context.setAttribute("daoFournisseurs", daoFournisseurs, javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        }
      }
      out.write("\n");
      out.write("        ");

            List<Fournisseurs> fournisseursList = daoFournisseurs.findAll();
            for(Fournisseurs fournisseurs : fournisseursList) {
        
      out.write("\n");
      out.write("        <div class=\"panel panel-default text-center\">\n");
      out.write("            <div class=\"panel-heading bg-dark\">\n");
      out.write("                <strong class=\"panel-title text-white\">");
      out.print(fournisseurs.getNom());
      out.write("</strong>\n");
      out.write("            </div>\n");
      out.write("            <table class=\"panel-body table table-hover table-bordered table-striped narrower table-color-grey\">\n");
      out.write("                <thead>\n");
      out.write("\n");
      out.write("                     ");

                         String header = Commandes.getHeader();
                     
      out.write("\n");
      out.write("                    ");
      out.print(header);
      out.write("\n");
      out.write("                </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody class=\"bg-lightgray\">\n");
      out.write("                ");

                        List<Commandes> commandesList = daoCommande.findByFno(fournisseurs.getFno());
                        for (Commandes commandes : commandesList){
                            String ligne = commandes.getHTML();
                
      out.write("\n");
      out.write("                            ");
      out.print(ligne);
      out.write("\n");
      out.write("                ");

                        }

                
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("    </div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
