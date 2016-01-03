package com.example.robpercival.webviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        //webView.loadUrl("http://www.google.com");

        webView.loadDataWithBaseURL("http://spacy.io/displacy/", "\n" +
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<title>displaCy Demo</title>\n" +
                "<meta charset=\"utf-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=0.75\">\n" +
                "<link rel=\"stylesheet\" href=\"css/displacy-demo.css\"/>\n" +
                "</head>\n" +
                "<body class=\"theme-code\" onload=\"runDemo()\">\n" +
                "<header>\n" +
                "<div class=\"logo\">\n" +
                "<span>displaCy &mdash; Dependency Parse Tree Visualization</span>\n" +
                "</div>\n" +
                "<button class=\"mobile\" onclick=\"toggle('nav')\"><span>Navigation</span></button>\n" +
                "<nav id=\"nav\">\n" +
                "<ul>\n" +
                "<li class=\"search-field\">\n" +
                "<input id=\"input\" type=\"text\" placeholder=\"Type a sentence...\" onkeydown=\"if (event.keyCode == 13) runDemo('full')\"/>\n" +
                "</li>\n" +
                "<li class=\"search\">\n" +
                "<button onclick=\"runDemo('full')\">Parse</button>\n" +
                "<button onclick=\"runDemo('steps')\">Step through</button>\n" +
                "<button onclick=\"runDemo('manual')\">Annotate</button>\n" +
                "<button onclick=\"toggle('share')\" class=\"share\">\n" +
                "<span>Share link</span>\n" +
                "<svg height=\"1em\" width=\"1em\" viewBox=\"0 0 100 100\" xmlns=\"http://www.w3.org/2000/svg\"><path d=\"M768 256h-138c48 32 93 89 107 128h30c65 0 128 64 128 128s-65 128-128 128h-192c-63 0-128-64-128-128 0-23 7-45 18-64h-137c-5 21-8 42-8 64 0 128 127 256 255 256s65 0 193 0 256-128 256-256-128-256-256-256z m-481 384h-30c-65 0-128-64-128-128s65-128 128-128h192c63 0 128 64 128 128 0 23-7 45-18 64h137c5-21 8-42 8-64 0-128-127-256-255-256s-65 0-193 0-256 128-256 256 128 256 256 256h138c-48-32-93-89-107-128z\"/></svg>\n" +
                "</button>\n" +
                "</li>\n" +
                "<li>\n" +
                "<select id=\"theme\" class=\"theme\">\n" +
                "<option value=\"\" disabled selected>Themes</option>\n" +
                "<option value=\"theme-code\">Code</option>\n" +
                "<option value=\"theme-code-light\">Code light</option>\n" +
                "<option value=\"theme-textbook\">Print</option>\n" +
                "<option value=\"theme-modern\">Modern</option>\n" +
                "<option value=\"theme-under-construction\">Under Construction</option>\n" +
                "</select>\n" +
                "</li>\n" +
                "<li><button onclick=\"toggle('info')\" class=\"info\">Info</button></li>\n" +
                "</ul>\n" +
                "</nav>\n" +
                "</header>\n" +
                "<div id=\"info\" class=\"modal\">\n" +
                "<div>\n" +
                "<a class=\"close\" onclick=\"toggle('info')\"></a>\n" +
                "<h2>About displaCy</h2>\n" +
                "<p>displaCy lets you peek inside a\n" +
                "spaCy's syntactic parser, as it reads a sentence word-by-word. By repeatedly choosing from a small set of actions, it links the words together according to their syntactic structure. This type of representation powers a wide range of technologies, from translation and summarization, to sentiment analysis and algorithmic trading.</p>\n" +
                "<p>displaCy makes <a href=\"http://ines.io/blog/developing-displacy\">clever use of JavaScript and CSS</a>, to do as little as possible and no less. It uses modern web standards to work with the browser, instead of against it.</p>\n" +
                "<p>\n" +
                "<a href=\"http://twitter.com/share?text=displaCy –  Dependency Parse Tree Visualization&url=http://spacy.io/displacy&via=spacy_io\" target=\"_blank\" class=\"button\">Share on Twitter</a>\n" +
                "<a href=\"http://www.twitter.com/spacy_io\" target=\"_blank\" class=\"button\">Follow spaCy</a>\n" +
                "<a href=\"http://spacy.io\" target=\"_blank\" class=\"button\">Find out more</a>\n" +
                "</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id=\"share\" class=\"modal\">\n" +
                "<div>\n" +
                "<a class=\"close\" onclick=\"toggle('share')\"></a>\n" +
                "<h2>Share</h2>\n" +
                "<p>Copy and paste this link to share the current visualization.</p>\n" +
                "<input id=\"share-link\" class=\"share-link\" onclick=\"select(this)\"/>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id=\"displacy\"></div>\n" +
                "<script>\n" +
                "        var sample = \"displaCy uses CSS and JavaScript to show you how computers understand language\"\n" +
                "\n" +
                "        var url = (location.search.split('?')[1]) ? location.search.split('?')[1].split('=') : null; \n" +
                "        var urlmode = (url) ? decodeURIComponent(url[0]) : null;\n" +
                "        var urltext = (url && urlmode) ? decodeURIComponent(url[1]) : null;\n" +
                "        \n" +
                "        var apis = {\n" +
                "            full: \"http://5.9.60.203/api/displacy/parse/\",\n" +
                "            steps: \"http://5.9.60.203/api/displacy/steps/\",\n" +
                "            manual: \"http://5.9.60.203/api/displacy/manual/\"\n" +
                "        }\n" +
                "\n" +
                "        var runDemo = function(mode) {\n" +
                "            var query = document.getElementById(\"input\").value || urltext || sample;\n" +
                "            var modes = (mode) ? mode : urlmode || 'full';\n" +
                "            displaCy(modes, apis[modes], query);\n" +
                "\n" +
                "            document.getElementById(\"share-link\").value = \"http://spacy.io/displacy/?\" + encodeURIComponent(modes) + \"=\" + encodeURIComponent(query);\n" +
                "        }\n" +
                "\n" +
                "        var themeSelect = document.getElementById(\"theme\");\n" +
                "        themeSelect.onchange = function() {\n" +
                "            document.body.className = \"\";\n" +
                "            document.body.className = this.value;\n" +
                "        }\n" +
                "\n" +
                "        var toggle = function(id) {\n" +
                "            var e = document.getElementById(id);\n" +
                "            (e.style.visibility == \"visible\") ? e.style.visibility = \"hidden\" :\n" +
                "            e.style.visibility = \"visible\";\n" +
                "        }\n" +
                "    </script>\n" +
                "<script src=\"js/displacy.min.js\"></script>\n" +
                "</body>\n" +
                "</html>\n", "text/html", "UTF-8", "");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
