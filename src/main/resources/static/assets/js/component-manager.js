(() => {
    function r(l, h) {
        class a extends HTMLElement {
            constructor(c, n, i) {
                super();
                this.getAttribute("shadow") !== null ? (this.attachShadow({ mode: "open" }), (this.shadow = !0), (this.root = this.shadowRoot)) : ((this.shadow = !1), (this.root = this)),
                    (this.alpineJS = window.Alpine ? window.Alpine : !1);
            }
            disconnectedCallback() {}
            connectedCallback() {
                let c = "/components/" + h + ".html";
                fetch(c)
                    .then((n) => n.text())
                    .then((n) => {
                        let i = document.createElement("template");
                        (i.innerHTML = n),
                            this.root.appendChild(i.content.cloneNode(!0)),
                            this.shadow ||
                                this.root.querySelectorAll("style").forEach((e) => {
                                    let o = e.innerText;
                                    if (o.includes(":host")) {
                                        (o = o.replaceAll(":host", l)), e.remove();
                                        let s = document.createElement("style");
                                        (s.textContent = o), this.root.appendChild(s);
                                    }
                                }),
                            this.root.querySelectorAll("script[export]").forEach((t) => {
                                let e = document.createElement("script");
                                t
                                    .getAttributeNames()
                                    .filter((s) => s !== "export")
                                    .forEach((s) => {
                                        e.setAttribute(s, t.getAttribute(s));
                                    }),
                                    (e.textContent = t.innerHTML),
                                    this.root.appendChild(e),
                                    (e.onload = function () {
                                        let s = new CustomEvent(t.getAttribute("export"), { bubbles: !0, composed: !0 });
                                        window.dispatchEvent(s);
                                    }),
                                    t.remove();
                            }),
                            this.root.querySelectorAll("[slot]").forEach((t) => {
                                let e = t.getAttribute("slot"),
                                    o = this.root.querySelector('slot[name="' + e + '"]');
                                t.tagName === "TEMPLATE" ? o.after(t.content.cloneNode(!0)) : o.after(t.cloneNode(!0)), t.remove(), o.remove();
                            }),
                            this.shadow &&
                                this.root.querySelectorAll("slot").forEach((e) => {
                                    let o = e.assignedNodes()[0];
                                    e.before(o), e.remove();
                                }),
                            this.shadow && this.alpineJS && this.alpineJS.initTree(this.root);
                    });
            }
        }
        customElements.define(l, a);
    }
    window.WebComponent = r;
})();