

(def ^:dynamic x)

;;  Vars ensure safe use of mutable storage locations via thread isolation

;; Thread locally mutable is still possible
(println (binding [x 1] (set! x 2) x))

;; Transactional references (Refs) ensure safe shared use of mutable storage
;; locations via a software transactional memory (STM)

;; Refs are bound to a single storage location for their lifetime, and only
;; allow mutation of that location to occur within a transaction.

;; Clojure transactions should be easy to understand if you've ever used
;; database transactions - they ensure that all actions on Refs are atomic,
;; consistent, and isolated. Atomic means that every change to Refs made within
;; a transaction occurs or none do. Consistent means that each new value can be
;; checked with a validator function before allowing the transaction to commit.

;; Isolated means that no transaction sees the effects of any other transaction
;; while it is running. Another feature common to STMs is that, should a transaction
;; have a conflict while running, it is automatically retried.

;; There are many ways to do STMs (locking/pessimistic, lock-free/optimistic and
;; hybrids) and it is still a research problem. The Clojure STM uses multiversion
;; concurrency control with adaptive history queues for snapshot isolation, and
;; provides a distinct commute operation.

;; In practice, this means:
;; All reads of Refs will see a consistent snapshot of the 'Ref world' as of the starting point of the transaction (its 'read point'). The transaction will see any changes it has made. This is called the in-transaction-value.
;; All changes made to Refs during a transaction (via ref-set, alter or commute) will appear to occur at a single point in the 'Ref world' timeline (its 'write point').
;; No changes will have been made by any other transactions to any Refs that have been ref-set/altered/ensured by this transaction.
;; Changes may have been made by other transactions to any Refs that have been commuted by this transaction. That should be okay since the function applied by commute should be commutative.
;; Readers and commuters will never block writers, commuters, or other readers.
;; Writers will never block commuters, or readers.
;; I/O and other activities with side-effects should be avoided in transactions, since transactions will be retried. The io! macro can be used to prevent the use of an impure function in a transaction.
;; If a constraint on the validity of a value of a Ref that is being changed depends upon the simultaneous value of a Ref that is not being changed, that second Ref can be protected from modification by calling ensure. Refs 'ensured' this way will be protected (item #3), but don't change the world (item #2).
;; The Clojure MVCC STM is designed to work with the persistent collections, and it is strongly recommended that you use the Clojure collections as the values of your Refs. Since all work done in an STM transaction is speculative, it is imperative that there be a low cost to making copies and modifications. Persistent collections have free copies (just use the original, it can't be changed), and 'modifications' share structure efficiently. In any case:
;; The values placed in Refs must be, or be considered, immutable!! Otherwise, Clojure can't help you.