(defproject babashka/babashka
  #=(clojure.string/trim
     #=(slurp "resources/BABASHKA_VERSION"))
  :description "babashka"
  :url "https://github.com/babashka/babashka"
  :scm {:name "git"
        :url "https://github.com/babashka/babashka"}
  :license {:name "Eclipse Public License 1.0"
            :url "http://opensource.org/licenses/eclipse-1.0.php"}
  :source-paths ["src" "sci/src" "babashka.curl/src" "fs/src" "pods/src"
                 "babashka.nrepl/src" "depstar/src" "process/src"
                 "deps.clj/src" "deps.clj/resources"]
  ;; for debugging Reflector.java code:
  ;; :java-source-paths ["sci/reflector/src-java"]
  :java-source-paths ["src-java"]
  :resource-paths ["resources" "sci/resources"]
  :dependencies [[org.clojure/clojure "1.11.0-alpha1"]
                 [borkdude/edamame "0.0.11"]
                 [borkdude/graal.locking "0.0.2"]
                 [org.clojure/tools.cli "1.0.206"]
                 [cheshire "5.10.0"]
                 [nrepl/bencode "1.1.0"]
                 [borkdude/sci.impl.reflector "0.0.1"]
                 [org.clojure/test.check "1.1.0"]]
  :profiles {:feature/xml  {:source-paths ["feature-xml"]
                            :dependencies [[org.clojure/data.xml "0.2.0-alpha6"]]}
             :feature/yaml {:source-paths ["feature-yaml"]
                            :dependencies [[clj-commons/clj-yaml "0.7.106"]]}
             :feature/jdbc {:source-paths ["feature-jdbc"]
                            :dependencies [[seancorfield/next.jdbc "1.1.610"]]}
             :feature/postgresql [:feature/jdbc {:dependencies [[org.postgresql/postgresql "42.2.18"]]}]
             ;:feature/oracledb [:feature/jdbc {:dependencies [[com.oracle.database.jdbc/ojdbc8 "19.8.0.0"]]}]
             :feature/oracledb [:feature/jdbc {:dependencies [[io.helidon.integrations.db/ojdbc "2.1.0"]]}] ; ojdbc10 + GraalVM config, by Oracle
             :feature/hsqldb [:feature/jdbc {:dependencies [[org.hsqldb/hsqldb "2.5.1"]]}]
             :feature/core-async {:source-paths ["feature-core-async"]
                                  :dependencies [[org.clojure/core.async "1.3.610"]]}
             :feature/csv {:source-paths ["feature-csv"]
                           :dependencies [[org.clojure/data.csv "1.0.0"]]}
             :feature/transit {:source-paths ["feature-transit"]
                               :dependencies [[com.cognitect/transit-clj "1.0.324"]]}
             :feature/datascript {:source-paths ["feature-datascript"]
                                  :dependencies [[datascript "1.0.1"]]}
             :feature/httpkit-client {:source-paths ["feature-httpkit-client"]
                                      :dependencies [[http-kit "2.5.3"]]}
             :feature/httpkit-server {:source-paths ["feature-httpkit-server"]
                                      :dependencies [[http-kit "2.5.3"]]}
             :feature/lanterna {:source-paths ["feature-lanterna"]
                                :dependencies [[babashka/clojure-lanterna "0.9.8-SNAPSHOT"]]}
             :feature/core-match {:source-paths ["feature-core-match"]
                                  :dependencies [[org.clojure/core.match "1.0.0"]]}
             :feature/hiccup {:source-paths ["feature-hiccup"]
                              :dependencies [[hiccup/hiccup "2.0.0-alpha2"]]}
             :feature/test-check {:source-paths ["feature-test-check"]}
             :feature/spec-alpha {:source-paths ["feature-spec-alpha"]}
             :feature/rewrite-clj {:source-paths ["feature-rewrite-clj"]
                                   :dependencies [[rewrite-clj/rewrite-clj "1.0.605-alpha"]]}
             :feature/selmer {:source-paths ["feature-selmer"]
                              :dependencies [[selmer/selmer "1.12.40"]]}
             :test [:feature/xml
                    :feature/lanterna
                    :feature/yaml
                    :feature/postgresql
                    :feature/hsqldb
                    :feature/core-async
                    :feature/csv
                    :feature/transit
                    :feature/datascript
                    :feature/httpkit-client
                    :feature/httpkit-server
                    :feature/core-match
                    :feature/hiccup
                    :feature/test-check
                    :feature/spec-alpha
                    :feature/rewrite-clj
                    :feature/selmer
                    {:dependencies [[com.clojure-goes-fast/clj-async-profiler "0.4.1"]
                                    [com.opentable.components/otj-pg-embedded "0.13.3"]]}]
             :uberjar {:global-vars {*assert* false}
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"
                                  "-Dclojure.spec.skip-macros=true"
                                  "-Dborkdude.dynaload.aot=true"]
                       :main babashka.main
                       :aot :all}
             :reflection {:main babashka.impl.classes/generate-reflection-file}}
  :aliases {"bb" ["with-profile" "test"  "run" "-m" "babashka.main"]}
  :deploy-repositories [["clojars" {:url "https://clojars.org/repo"
                                    :username :env/clojars_user
                                    :password :env/clojars_pass
                                    :sign-releases false}]])
