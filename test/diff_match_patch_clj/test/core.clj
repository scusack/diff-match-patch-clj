(ns diff-match-patch-clj.test.core
  (:use [diff-match-patch-clj.core] :reload)
  (:use [clojure.test]
        [clojure.pprint :only [pprint]]))

(deftest test-diff
  (is (= (.toString (diff "test" "this"))
         "[Diff(EQUAL,\"t\"), Diff(DELETE,\"e\"), Diff(INSERT,\"hi\"), Diff(EQUAL,\"s\"), Diff(DELETE,\"t\")]"))

  (is (= (.toString (-> (diff "test" "this")
                        cleanup!))
         "[Diff(EQUAL,\"t\"), Diff(DELETE,\"est\"), Diff(INSERT,\"his\")]"))

  (is (= (.toString (-> (diff "test" "this")
                        cleanup!
                        htmlize))
         "<span>t</span><del style=\"background:#ffe6e6;\">est</del><ins style=\"background:#e6ffe6;\">his</ins>")))

(deftest test-pretty-html
  (is (= (.toString (-> (diff "test" "this")
                        cleanup!
                        pretty-html))
         "<span>t</span><del style=\"background:#ffe6e6;\">est</del><ins style=\"background:#e6ffe6;\">his</ins>")))

(deftest test-as-hiccup
  (is (= (-> (diff "test" "this")
             cleanup!
             as-hiccup)
         [[:span nil "t"] [:del nil "est"] [:ins nil "his"]]))

  (is (= (-> (diff "test" "this")
             cleanup!
             (as-hiccup :ins-attrs {:class :inserted}))
         [[:span nil "t"] [:del nil "est"] [:ins {:class :inserted} "his"]])))
