# `diff-match-patch-clj`

Simple wrapping of the Neil Fraser's
[google-diff-match-patch](http://code.google.com/p/google-diff-match-patch/)
library.

## Usage

The main functionality is provided by the
`diff-match-patch-clj.core` namespace:

```clojure
(use 'diff-match-patch-clj.core)
```

You can create your own diff-match-patch object or use the default one
by calling the wrappers.

Currently has diff, cleanup!, pretty-html and as-hiccup.

```clojure
(diff "test" "this")
=> [Diff(EQUAL,"t"), Diff(DELETE,"e"), Diff(INSERT,"hi"), Diff(EQUAL,"s"), Diff(DELETE,"t")]

(-> (diff "test" "this")
     cleanup!)
=> [Diff(EQUAL,"t"), Diff(DELETE,"est"), Diff(INSERT,"his")]

(-> (diff "test" "this")
    cleanup!
    as-hiccup)
=> [[:span nil "t"] [:del nil "est"] [:ins nil "his"]]
```

## Installation

`diff-match-patch-clj` is available as a Maven artifact from
[Clojars](http://clojars.org/diff-match-patch-clj):

```clojure
[diff-match-patch-clj "1.0.0-SNAPSHOT"]
```

## License

Released under the Apache 2.0 License (same as google-diff-match-patch):
http://www.apache.org/licenses/LICENSE-2.0
