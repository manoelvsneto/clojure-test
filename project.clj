(defproject clojure-crud-api "0.1.0-SNAPSHOT"
  :description "Cloujure Teste"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring "1.7.1"]
                 [ring/ring-json "0.5.0"]
                 [compojure "1.6.1"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [com.microsoft.sqlserver/mssql-jdbc "6.3.6.jre8-preview"] ]
  :repl-options {:init-ns clojure-crud-api.core}
  :main clojure-crud-api.core)
