/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2018-09-20 00:29:49 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
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
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html lang=\"en\">\n");
      out.write("  \n");
      out.write("<head>\n");
      out.write("    <link href=\"assets/css/vendors.min.css\" rel=\"stylesheet\" />\n");
      out.write("    <link href=\"assets/css/styles.min.css\" rel=\"stylesheet\" />\n");
      out.write("    <link rel=\"apple-touch-icon\" sizes=\"57x57\" href=\"");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("\">\n");
      out.write("    <script  src=\"http://maps.google.com/maps/api/js?sensor=true\"></script> \n");
      out.write("  </head>\n");
      out.write("  <body class=\"page-login\" init-ripples=\"\">\n");
      out.write("    <div class=\"center\">\n");
      out.write("      <div class=\"card bordered z-depth-2\" style=\"margin:0% auto; max-width:400px; max-height: none;\">\n");
      out.write("        <div class=\"card-header\">\n");
      out.write("          <div class=\"brand-logo\">\n");
      out.write("            <img src=\"assets/img/logo_2.png\"> </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("                    ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("        <div class=\"card-content trx_push_left\">\n");
      out.write("          <div class=\"m-b-30\">\n");
      out.write("            <div class=\"card-title strong pink-text\">Login</div>\n");
      out.write("            <p class=\"card-title-desc\"> Welcome to Krenai! Let's be closely now </p>\n");
      out.write("          </div>\n");
      out.write("          <form class=\"form-floating\" method=\"post\" onsubmit=\"return validateIndexPage();\" action= \"");
      if (_jspx_meth_c_005furl_005f1(_jspx_page_context))
        return;
      out.write("\">\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("              <label for=\"inputEmail\" class=\"control-label\">Email</label>\n");
      out.write("              <input type=\"text\" class=\"form-control\" name=\"username\" onchange=\"return validate();\" id=\"inputUsername\">\n");
      out.write("              <div class=\"help-block with-errors validationText\" id=\"inputUsernameValidation\"></div>\n");
      out.write("               </div>\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("              <label for=\"inputPassword\" class=\"control-label\">Password</label>\n");
      out.write("              <input type=\"password\" name=\"password\" class=\"form-control\" id=\"inputPassword\" autocomplete=\"off\"> \n");
      out.write("              <div class=\"help-block with-errors validationText\" id=\"inputPasswordValidation\"></div>\n");
      out.write("              </div>\n");
      out.write("            <div class=\"form-group\">\n");
      out.write("              <div class=\"checkbox\">\n");
      out.write("                <label>\n");
      out.write("                  <input type=\"checkbox\"> Remember me </label>\n");
      out.write("              </div>\n");
      out.write("            </div>\n");
      out.write("             <div class=\"card-action clearfix\">\n");
      out.write("          <div class=\"pull-right\">\n");
      out.write("\n");
      out.write("\n");
      out.write("           \n");
      out.write("\n");
      out.write("            <div class=\"row\">\n");
      out.write("              <div class=\"col-md-6\">\n");
      out.write("                 <button type=\"submit\" class=\"btn btn-link black-text\">Forgot password</button>\n");
      out.write("              </div>\n");
      out.write("              <div class=\"col-md-4\">\n");
      out.write("                <input id=\"sub-btn\" class=\"btn btn-link black-text\"  type=\"submit\" value=\"Login\" onclick=\"return userPasswardValue(this.value,this.id);spinner();Javascript:checkEmail();\"/ >\n");
      out.write("              <input id=\"sub-btn-demo\" style=\"display:none;\" class=\"btn btn-link black-text\"  type=\"button\" value=\"Login\" / >\n");
      out.write("              </div>\n");
      out.write("              <div class=\"col-md-2\">\n");
      out.write("                <div id=\"spinner\" ></div>\n");
      out.write("              </div>\n");
      out.write("           </div>\n");
      out.write("\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("          </form>\n");
      out.write("        </div>\n");
      out.write("       \n");
      out.write("      </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("   \n");
      out.write("   \n");
      out.write("  </body>\n");
      out.write("\t<script src=\"");
      if (_jspx_meth_c_005furl_005f2(_jspx_page_context))
        return;
      out.write("\"></script>\n");
      out.write("    <script src=\"");
      if (_jspx_meth_c_005furl_005f3(_jspx_page_context))
        return;
      out.write("\"></script>\n");
      out.write("     <script src=\"");
      if (_jspx_meth_c_005furl_005f4(_jspx_page_context))
        return;
      out.write("\"></script>    \n");
      out.write("    <script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("    function validateEmail(email) { \n");
      out.write("  \n");
      out.write("    var re = /^(([^<>()[\\]\\\\.,;:\\s@\\\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$/;\n");
      out.write("    return re.test(email);\n");
      out.write("}\n");
      out.write("\n");
      out.write("function validate(){\n");
      out.write("  \n");
      out.write("  $(\"#inputUsernameValidation\").text(\"\");\n");
      out.write("  var email = $(\"#inputUsername\").val();\n");
      out.write("  if (validateEmail(email)) {\n");
      out.write("\n");
      out.write("    $(\"#inputUsernameValidation\").text(\"Please input correct email address.\").hide();\n");
      out.write("            $(\"#inputUsername\").css(\"border-bottom\", \"1px \");\n");
      out.write("            return true;\n");
      out.write("  } else {\n");
      out.write("    $(\"#inputUsernameValidation\").text(\"Please input correct email address.\").show();\n");
      out.write("    $(\"#inputUsername\").css(\"border-bottom\", \"1px  solid #FF0000\");\n");
      out.write("    $(\"#inputUsername\").focus();\n");
      out.write("              return false;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("}\n");
      out.write("function validateIndexPage(){\n");
      out.write("  $(\"#sub-btn\").css(\"display\",\"none\");\n");
      out.write("  $(\"#sub-btn-demo\").css(\"display\",\"block\");\n");
      out.write("  spinner();\n");
      out.write("  validate();\n");
      out.write("  userPasswardValue();\n");
      out.write("  if(validate()){\n");
      out.write("    if(userPasswardValue()){\n");
      out.write("      return true;\n");
      out.write("    }\n");
      out.write("  }\n");
      out.write("  $(\"#sub-btn-demo\").css(\"display\",\"none\");\n");
      out.write("  $(\"#sub-btn-demo\").css(\"display\",\"block\");\n");
      out.write("  $(\"#spinner\").empty(300);\n");
      out.write("  return false;\n");
      out.write("}\n");
      out.write("\n");
      out.write("    </script>\n");
      out.write("\n");
      out.write("                   <script>\n");
      out.write("       function spinner(){\n");
      out.write("         // $(\"#add-shop-btn\").attr(\"disabled\",true);\n");
      out.write("         $(\"#add-product-form\").submit();\n");
      out.write("         $(\"#spinner\").css(\"text-align\",\"right\");\n");
      out.write("         \n");
      out.write("        $(\"#spinner\").append(\"<svg width='32px' height='32px' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100' preserveAspectRatio='xMidYMid' class='uil-ring-alt'><rect x='0' y='0' width='100' height='100' fill='none' class='bk'></rect><circle cx='50' cy='50' r='40' stroke='#f0f0f0' fill='none' stroke-width='10' stroke-linecap='round'></circle><circle cx='50' cy='50' r='40' stroke='#e91e63' fill='none' stroke-width='6' stroke-linecap='round'><animate attributeName='stroke-dashoffset' dur='2s' repeatCount='indefinite' from='0' to='502'></animate><animate attributeName='stroke-dasharray' dur='2s' repeatCount='indefinite' values='150.6 100.4;1 250;150.6 100.4'></animate></circle></svg>\");\n");
      out.write("        }\n");
      out.write("             \n");
      out.write("       </script>\n");
      out.write("</html>");
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

  private boolean _jspx_meth_c_005furl_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f0.setParent(null);
    // /index.jsp(12,53) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("/assets/img/favicon/apple-touch-icon-57x57.html");
    int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
    if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /index.jsp(23,20) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${shiroLoginFailure != null}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                      <div class=\"alert alert-block alert-danger\" >\n");
        out.write("                        <button data-dismiss=\"alert\" class=\"close\">\n");
        out.write("                          &times;\n");
        out.write("                        </button>\n");
        out.write("                        <i class=\"fa fa-times-circle\"></i>&nbsp;\n");
        out.write("                        Username and Password are Mismatch. Please Try again..\n");
        out.write("                      </div>\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f1 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f1.setParent(null);
    // /index.jsp(37,100) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f1.setValue("/login");
    int _jspx_eval_c_005furl_005f1 = _jspx_th_c_005furl_005f1.doStartTag();
    if (_jspx_th_c_005furl_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f2 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f2.setParent(null);
    // /index.jsp(85,14) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f2.setValue("assets/js/vendors.min.js");
    int _jspx_eval_c_005furl_005f2 = _jspx_th_c_005furl_005f2.doStartTag();
    if (_jspx_th_c_005furl_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f3(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f3 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f3.setParent(null);
    // /index.jsp(86,17) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f3.setValue("assets/js/app.min.js");
    int _jspx_eval_c_005furl_005f3 = _jspx_th_c_005furl_005f3.doStartTag();
    if (_jspx_th_c_005furl_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f4(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f4 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f4.setParent(null);
    // /index.jsp(87,18) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f4.setValue("assets/js/myFormValidation.js");
    int _jspx_eval_c_005furl_005f4 = _jspx_th_c_005furl_005f4.doStartTag();
    if (_jspx_th_c_005furl_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f4);
    return false;
  }
}
