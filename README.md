# Secret Storage Management Using TTL Virtual Masks

This project demonstrates a mechanism that can be used to share secrets (identification secrets such as SSN) among personal without the need to share the actual secret. This is a centralized system that gives the users a fine-grained control of how to share secrets to different merchants. This is achived using the following techniques:
1. Virtual Masks: This involves creating a "fake" unique secret using the actual secret that is specific to a particular merchant to whom you want to share the secret with.
2. TTL Tokens: This involves attaching a expiry time on the virtual masks created. Once expires, the merchant can no longer access the secret.

TO UNDERSTAND THE ARCHITECTURE OF THE ENTIRE SYSTEM AND FOR OTHER FINER DETAILS, PLEASE GO THROUGH "Sunhacks Demo.pdf"
