//
//  LocationPickerViewController.swift
//  ChatApp
//
//  Created by user191721 on 4/8/21.
//

import UIKit
import CoreLocation
import MapKit

final class LocationPickerViewController: UIViewController {

    public var completion: ((CLLocationCoordinate2D) -> Void)?
    private var coordinates: CLLocationCoordinate2D?
    private var isPickable = true
    
    private var map: MKMapView = {
        let map = MKMapView()
        return map
    }()
    
    init(coordinates: CLLocationCoordinate2D?) {
        super.init(nibName: nil, bundle: nil)
        self.isPickable = coordinates == nil
        self.coordinates = coordinates
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = .systemBackground
        if isPickable {
            navigationItem.rightBarButtonItem = UIBarButtonItem(title:"Send", style: .done, target: self, action: #selector(sendButtonTapped))
                
            map.isUserInteractionEnabled = true
            let gesture = UITapGestureRecognizer(target:self, action: #selector(didTapMap(_:)))
            gesture.numberOfTouchesRequired = 1
            gesture.numberOfTapsRequired = 1
            map.addGestureRecognizer(gesture)
        }
        else{
            // just showing the location sent
            guard let coordinates = self.coordinates else {
                return
            }
            //add the pin
            let pin = MKPointAnnotation()
            pin.coordinate = coordinates
            let coordinateRegion = MKCoordinateRegion(center: pin.coordinate, latitudinalMeters: 10000, longitudinalMeters: 10000)
            map.setRegion(coordinateRegion, animated: true)
            map.addAnnotation(pin)
        }
        view.addSubview(map)

    }
    
    @objc func sendButtonTapped() {
        guard let coordinates = coordinates else {
            return
        }
        navigationController?.popViewController(animated: true)
        completion?(coordinates)
    }
    
    @objc func didTapMap(_ gesture: UITapGestureRecognizer) {
        let locationInView = gesture.location(in: map)
        let coordinates = map.convert(locationInView, toCoordinateFrom: map)
        self.coordinates = coordinates
        
        for annotation in map.annotations {
            map.removeAnnotation(annotation)
        }
        
        //put a pin on the location
        let pin = MKPointAnnotation()
        pin.coordinate = coordinates
        map.addAnnotation(pin)
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        map.frame = view.bounds
    }

}
