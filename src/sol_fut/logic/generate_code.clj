(ns sol-fut.logic.generate-code)

(defn code-layout-random [acc _]
  (if (= acc 0)
    (str (rand-int 10) (rand-int 10))
    (str acc (rand-int 10))))

(defn generate-code []
  (let [layout [0 0 0 0]]
    (reduce code-layout-random layout)))