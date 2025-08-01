#  Router Simulator (Java + Swing)
A simple educational project that simulates how routers forward packets based on a routing table using IP addressing and subnet matching. Built with Java and Swing, this tool demonstrates the core concept of **packet forwarding**, **longest prefix matching**, and **IP routing logic** - just like how the internet routes data between devices.

## 🔎 Overview

This project simulates how routers process and forward packets based on a **destination IP address** and a **routing table**. The GUI allows users to:

- Add routing entries (Destination IP, Subnet Mask, Next Hop)
- Enter a destination IP to simulate a packet being sent
- Check if the router forwards or drops the packet
- View routing decisions and longest prefix match in action

## ✨ Features

- Longest Prefix Matching logic (like real routers)
- Add or delete routing table entries
- Interactive Swing GUI for input/output
- Packet forwarding result shown clearly (Forwarded or Dropped)
- CLI version also available (optional)

## 🌐 How Routing Works

When a router receives a data packet, it looks at the **destination IP address** and decides where to forward the packet. It does this using a **routing table**.

### Routing Steps:

1. **Convert IP to Binary:**  
   Both destination IP and routing table entries are converted into binary format.

2. **Match with Subnet Mask:**  
   Using the subnet mask, the router compares the destination IP with the entries in the table.

3. **Longest Prefix Match:**  
   It chooses the route with the longest matching prefix bits (most specific match).

4. **Forward or Drop:**  
   - If a match is found → forward to the `next hop`
   - If not → packet is dropped
