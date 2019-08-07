<?xml version="1.0" encoding='ISO-8859-1' ?>
<!DOCTYPE helpset
  PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 1.0//EN"
         "http://java.sun.com/products/javahelp/helpset_1_0.dtd">
 

<helpset version="1.0">
        <title>ARBORE</title>
        <maps>
                <!-- Pagina inicial da ajuda -->
                <homeID>aplicacion</homeID>
                <!-- indicação do mapa -->
                <mapref location="map_file.jhm"/>
        </maps>

        <!-- abas da ajuda -->
        <view>
                <name>topicosDeAjuda</name>
                <label>Tópicos da Ajuda</label>
                <type>javax.help.TOCView</type>
                <data>toc.xml</data>
        </view>

  <view>
    <name>indice</name>
    <label>Índice</label>
    <type>javax.help.IndexView</type>
    <data>indice.xml</data>
  </view>
  
  <view>
    <name>procurar</name>
    <label>Procurar</label>
    <type>javax.help.SearchView</type>
    <data engine="com.sun.java.help.search.DefaultSearchEngine">
      JavaHelpSearch
    </data>
  </view>
  
</helpset>

